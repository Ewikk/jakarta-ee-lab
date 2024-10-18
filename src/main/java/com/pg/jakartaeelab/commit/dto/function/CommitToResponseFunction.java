package com.pg.jakartaeelab.commit.dto.function;

import com.pg.jakartaeelab.commit.dto.GetCommitResponse;
import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.user.dto.GetUsersResponse;

import java.util.function.Function;

public class CommitToResponseFunction implements Function<Commit, GetCommitResponse> {
    @Override
    public GetCommitResponse apply(Commit commit) {
        return GetCommitResponse.builder()
                .id(commit.getId())
                .author(GetUsersResponse.User.builder()
                        .login(commit.getAuthor().getLogin())
                        .id(commit.getAuthor().getId())
                        .build())
                .filesChangedCount(commit.getFilesChangedCount())
                .message(commit.getMessage())
                .timestamp(commit.getTimestamp())
                .gitRepository(GetCommitResponse.GitRepository.builder()
                        .name(commit.getGitRepository().getName())
                        .uuid(commit.getGitRepository().getId())
                        .build())
                .build();
    }
}
