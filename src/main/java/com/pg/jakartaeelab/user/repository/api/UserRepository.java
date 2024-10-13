package com.pg.jakartaeelab.user.repository.api;

import com.pg.jakartaeelab.repository.api.Repository;
import com.pg.jakartaeelab.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {

    Optional<User> findByLogin(String login);
}
