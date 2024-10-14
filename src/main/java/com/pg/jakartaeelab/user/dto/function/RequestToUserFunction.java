package com.pg.jakartaeelab.user.dto.function;

import com.pg.jakartaeelab.user.dto.PutUserRequest;
import com.pg.jakartaeelab.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {
    @Override
    public User apply(UUID uuid, PutUserRequest request) {
        return User.builder()
                .id(uuid)
                .login(request.getLogin())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }
}
