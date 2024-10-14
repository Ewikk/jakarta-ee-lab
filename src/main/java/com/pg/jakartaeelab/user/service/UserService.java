package com.pg.jakartaeelab.user.service;

import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository repository;

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

    public void updateAvatar(UUID id, InputStream is) {
        repository.find(id).ifPresent(user -> {
            try {
                user.setAvatar(is.readAllBytes());
                repository.update(user);
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
