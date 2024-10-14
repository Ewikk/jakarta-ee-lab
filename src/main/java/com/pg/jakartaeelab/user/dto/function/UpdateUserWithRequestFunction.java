package com.pg.jakartaeelab.user.dto.function;

import com.pg.jakartaeelab.user.dto.PatchUserRequest;
import com.pg.jakartaeelab.user.entity.User;

import java.util.function.BiFunction;

public class UpdateUserWithRequestFunction implements BiFunction<User, PatchUserRequest, User> {

    @Override
    public User apply(User user, PatchUserRequest request) {
        return User.builder()
                .id(user.getId())
                .login(request.getLogin())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }
}
