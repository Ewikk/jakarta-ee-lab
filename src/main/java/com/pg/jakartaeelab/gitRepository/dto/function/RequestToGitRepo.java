package com.pg.jakartaeelab.gitRepository.dto.function;

import com.pg.jakartaeelab.gitRepository.dto.PutGitRepoRequest;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToGitRepo implements BiFunction<UUID, PutGitRepoRequest, GitRepository> {
    @Override
    public GitRepository apply(UUID uuid, PutGitRepoRequest putGitRepoRequest) {
        return GitRepository.builder()
                .id(uuid)
                .name(putGitRepoRequest.getName())
                .visibility(putGitRepoRequest.getVisibility())
                .owner(User.builder().id(putGitRepoRequest.getOwnerId()).build())
                .creationDate(putGitRepoRequest.getCreationDate())
                .build();
    }
}
