package com.pg.jakartaeelab.commit.dto.function;

import com.pg.jakartaeelab.commit.dto.GetCommitsResponse;
import com.pg.jakartaeelab.commit.dto.PutCommitRequest;
import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToCommitFunction implements BiFunction<UUID, PutCommitRequest, Commit> {
    @Override
    public Commit apply(UUID uuid, PutCommitRequest request) {
        return Commit.builder()
                .id(uuid)
                .author(User.builder()
                        .id(request.getAuthorId())
                        .build())
                .gitRepository(GitRepository.builder()
                        .id(request.getRepoId())
                        .build())
                .message(request.getMessage())
                .filesChangedCount(request.getFilesChangedCount())
                .timestamp(request.getTimestamp())
                .build();
    }
}
