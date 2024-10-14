package com.pg.jakartaeelab.user.repository.memory;

import com.pg.jakartaeelab.datastore.component.DataStore;
import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserInMemoryRepository implements UserRepository {

    private final DataStore dataStore;

    public UserInMemoryRepository(DataStore store) {
        this.dataStore = store;
    }

    @Override
    public Optional<User> find(UUID id) {
        return dataStore.findAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return dataStore.findAllUsers();
    }

    @Override
    public void create(User entity) {
        dataStore.createUser(entity);
    }

    @Override
    public void delete(User entity) {
        dataStore.deleteUser(entity);
    }

    @Override
    public void update(User entity) {
        dataStore.updateUser(entity);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return dataStore.findAllUsers().stream().filter(user -> user.getLogin().equals(login)).findFirst();
    }
}
