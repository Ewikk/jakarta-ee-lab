package com.pg.jakartaeelab.user.repository.memory;

import com.pg.jakartaeelab.datastore.component.DataStore;
import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserInMemoryRepository implements UserRepository {

    private final DataStore dataStore;
    @Override
    public Optional<User> find(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}
