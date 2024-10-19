package com.pg.jakartaeelab.user.controller.simple;

import com.pg.jakartaeelab.component.DtoFunctionFactory;
import com.pg.jakartaeelab.controller.servlet.exception.AlreadyExistsException;
import com.pg.jakartaeelab.controller.servlet.exception.BadRequestException;
import com.pg.jakartaeelab.controller.servlet.exception.NotFoundException;
import com.pg.jakartaeelab.user.controller.api.UserController;
import com.pg.jakartaeelab.user.dto.GetUserResponse;
import com.pg.jakartaeelab.user.dto.GetUsersResponse;
import com.pg.jakartaeelab.user.dto.PatchUserRequest;
import com.pg.jakartaeelab.user.dto.PutUserRequest;
import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UserSimpleController implements UserController {
    private final UserService service;
    private final DtoFunctionFactory factory;

    @Inject
    public UserSimpleController(UserService userService, DtoFunctionFactory dtoFunctionFactory) {
        service = userService;
        factory = dtoFunctionFactory;
    }

    @Override
    public GetUserResponse getUser(UUID id) {
        return service.find(id).map(factory.userToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetUsersResponse getUsers() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException();
        }
        return factory.usersToResponse().apply(users);
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) throws AlreadyExistsException{
        Optional<User> user = service.find(id);
        if(user.isPresent()){
            throw new AlreadyExistsException("User with id " + id + " already exists");
//            service.update(factory.requestToUser().apply(id, request));
//            return;
        }
        try {
            service.create(factory.requestToUser().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }
    }

    @Override
    public void patchUser(UUID id, PatchUserRequest request) {
        service.find(id).ifPresentOrElse(user -> service.update(factory.updateUserWithRequest().apply(user, request)), () -> {
            throw new NotFoundException();
        });
    }

    @Override
    public void deleteUser(UUID id) {
        service.find(id).ifPresentOrElse(
                service::delete,
                ()->{
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public byte[] getUserAvatar(UUID id, String path) {
        Path pathToAvatar = Paths.get(
                path,
                service.find(id)
                        .map(user -> user.getId().toString())
                        .orElseThrow(() -> new NotFoundException("User does not exist"))
                        + ".jpg"
        );
        try {
            if (!Files.exists(pathToAvatar)) {
                throw new NotFoundException("User avatar does not exist");
            }
            return Files.readAllBytes(pathToAvatar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar, String path) {
        service.find(id).ifPresentOrElse(
                user -> {
                    service.createAvatar(id, avatar, path);
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void patchUserAvatar(UUID id, InputStream avatar, String path) {
        service.find(id).ifPresentOrElse(
                user -> service.updateAvatar(id, avatar, path),
                () -> {
                    throw new NotFoundException("User does not exist");
                }
        );
    }

    @Override
    public void deleteUserAvatar(UUID id, String path) {
        service.find(id).ifPresentOrElse(
                user -> {
                    try {
                        Path avatarPath = Paths.get(path, user.getId().toString() + ".jpg");
                        if (!Files.exists(avatarPath)) {
                            throw new NotFoundException("User avatar does not exist");
                        }
                        Files.delete(avatarPath);
                    } catch (IOException e) {
                        throw new NotFoundException(e);
                    }
                },
                () -> {
                    throw new NotFoundException("User does not exist");
                }
        );
    }
}
