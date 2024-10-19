package com.pg.jakartaeelab.component;

import com.pg.jakartaeelab.commit.dto.function.CommitToResponseFunction;
import com.pg.jakartaeelab.commit.dto.function.CommitsToResponseFunction;
import com.pg.jakartaeelab.commit.dto.function.RequestToCommitFunction;
import com.pg.jakartaeelab.commit.dto.function.UpdateCommitWithRequestFunction;
import com.pg.jakartaeelab.user.dto.function.RequestToUserFunction;
import com.pg.jakartaeelab.user.dto.function.UpdateUserWithRequestFunction;
import com.pg.jakartaeelab.user.dto.function.UserToResponseFunction;
import com.pg.jakartaeelab.user.dto.function.UsersToResponseFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
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

    public CommitToResponseFunction commitToResponse() {
        return new CommitToResponseFunction();
    }

    public CommitsToResponseFunction commitsToResponse() {
        return new CommitsToResponseFunction();
    }

    public RequestToCommitFunction requestToCommit() {
        return new RequestToCommitFunction();
    }

    public UpdateCommitWithRequestFunction updateCommitWithRequestFunction(){
        return new  UpdateCommitWithRequestFunction();
    }
}
