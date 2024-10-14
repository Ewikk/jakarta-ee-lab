package com.pg.jakartaeelab.user.dto.function;

import com.pg.jakartaeelab.user.dto.GetUserResponse;
import com.pg.jakartaeelab.user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {

    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .dateOfBirth(user.getDateOfBirth())
                .login(user.getLogin())
                .build();
    }
}
