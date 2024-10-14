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

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserSimpleController implements UserController {
    private final UserService service;
    private final DtoFunctionFactory factory;

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
    public void putUser(UUID id, PutUserRequest request) {
        Optional<User> user = service.find(id);
        if(user.isPresent()){
            service.update(factory.requestToUser().apply(id, request));
            return;
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
    public byte[] getUserAvatar(UUID id) {
        return service.find(id)
                .map(User::getAvatar)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar) {
        service.find(id).ifPresentOrElse(
                user -> service.updateAvatar(id, avatar),
                ()->{
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void patchUserAvatar(UUID id, InputStream avatar) {
        service.find(id).ifPresentOrElse(
                user -> service.updateAvatar(id, avatar),
                ()->{
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteUserAvatar(UUID id) {
        service.find(id).ifPresentOrElse(
                user -> service.deleteAvatar(id),
                ()->{
                    throw new NotFoundException();
                }
        );
    }
}
