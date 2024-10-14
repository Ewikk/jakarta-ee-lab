package com.pg.jakartaeelab.user.dto.function;

import com.pg.jakartaeelab.user.dto.GetUsersResponse;
import com.pg.jakartaeelab.user.entity.User;

import java.util.List;
import java.util.function.Function;

public class UsersToResponseFunction implements Function<List<User>, GetUsersResponse> {
    @Override
    public GetUsersResponse apply(List<User> users) {
        return GetUsersResponse.builder().users(users.stream().map(user -> GetUsersResponse.User.builder().id(user.getId()).login(user.getLogin()).build()).toList()).build();
    }
}
