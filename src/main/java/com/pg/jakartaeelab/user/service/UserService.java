package com.pg.jakartaeelab.user.service;

import com.pg.jakartaeelab.controller.servlet.exception.AlreadyExistsException;
import com.pg.jakartaeelab.controller.servlet.exception.NotFoundException;
import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.repository.api.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserService {
    private final UserRepository repository;

    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> find(UUID id) {
        return repository.find(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> find(String login) {
        return repository.findByLogin(login);
    }

    public void create(User user) {
        repository.create(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void updateAvatar(UUID id, InputStream is, String path) {
        repository.find(id).ifPresent(user -> {
            try {
                Path existingPath = Path.of(path, id.toString() + ".jpg");
                if (Files.exists(existingPath)) {
                    Files.copy(is, existingPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    throw new NotFoundException("User avatar not found, use PUT instead.");
                }
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public void createAvatar(UUID id, InputStream is, String path) {
        repository.find(id).ifPresent(user -> {
            try {
                Path destinationPath = Path.of(path, id.toString() + ".jpg");
                if (Files.exists(destinationPath)) {
                    throw new AlreadyExistsException("Avatar already exists, use PATCH instead.");
                }
                Files.copy(is, destinationPath);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public void deleteAvatar(UUID id){
        repository.find(id).ifPresent(user -> {
            user.setAvatar(null);
            repository.update(user);
        });
    }
}
