package com.pg.jakartaeelab.gitRepository.dto.function;

import com.pg.jakartaeelab.gitRepository.dto.GitReposResponse;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;

import java.util.List;
import java.util.function.Function;

public class GitReposToResponseFunction implements Function<List<GitRepository>, GitReposResponse> {

    @Override
    public GitReposResponse apply(List<GitRepository> gitRepos) {
        return GitReposResponse.builder()
                .gitRepos(gitRepos.stream()
                        .map(repo -> GitReposResponse.GitRepo.builder()
                                .id(repo.getId())
                                .name(repo.getName())
                                .build())
                        .toList())
                .build();
    }
}
