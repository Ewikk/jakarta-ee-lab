package com.pg.jakartaeelab.component;

import com.pg.jakartaeelab.user.dto.function.RequestToUserFunction;
import com.pg.jakartaeelab.user.dto.function.UpdateUserWithRequestFunction;
import com.pg.jakartaeelab.user.dto.function.UserToResponseFunction;
import com.pg.jakartaeelab.user.dto.function.UsersToResponseFunction;

public class DtoFunctionFactory {
    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    public UpdateUserWithRequestFunction updateUserWithRequest() {
        return new UpdateUserWithRequestFunction();
    }
}
