package com.pg.jakartaeelab.gitRepository.dto.function;

import com.pg.jakartaeelab.gitRepository.dto.GetGitRepoResponse;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.user.dto.GetUsersResponse;

import java.util.function.Function;

public class GitRepoToResponseFunction implements Function<GitRepository, GetGitRepoResponse> {
    @Override
    public GetGitRepoResponse apply(GitRepository gitRepo) {
        return GetGitRepoResponse.builder()
                .id(gitRepo.getId())
                .name(gitRepo.getName())
                .owner(GetUsersResponse.User.builder()
                        .id(gitRepo.getOwner().getId())
                        .login(gitRepo.getOwner().getLogin())
                        .build())
                .build();
    }
}
