package com.pg.jakartaeelab.gitRepository.dto.function;

import com.pg.jakartaeelab.commit.dto.GetCommitsResponse;
import com.pg.jakartaeelab.gitRepository.dto.GitRepoResponse;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.user.dto.GetUsersResponse;

import java.util.function.Function;

public class GitRepoToResponseFunction implements Function<GitRepository, GitRepoResponse> {
    @Override
    public GitRepoResponse apply(GitRepository gitRepo) {
        return GitRepoResponse.builder()
                .id(gitRepo.getId())
                .name(gitRepo.getName())
                .owner(GetUsersResponse.User.builder()
                        .id(gitRepo.getOwner().getId())
                        .login(gitRepo.getOwner().getLogin())
                        .build())
//                .commits(gitRepo.getCommits().stream()
//                        .map(commit -> GetCommitsResponse.Commit.builder()
//                                .message(commit.getMessage())
//                                .id(commit.getId())
//                                .build())
//                        .toList())
                .creationDate(gitRepo.getCreationDate())
                .visibility(gitRepo.getVisibility())
                .build();
    }
}
