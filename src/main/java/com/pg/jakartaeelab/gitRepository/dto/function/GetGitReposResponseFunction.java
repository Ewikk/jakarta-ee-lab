package com.pg.jakartaeelab.gitRepository.dto.function;

import com.pg.jakartaeelab.gitRepository.dto.GetGitReposResponse;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;

import java.util.List;
import java.util.function.Function;

public class GetGitReposResponseFunction implements Function<List<GitRepository>, GetGitReposResponse> {

    @Override
    public GetGitReposResponse apply(List<GitRepository> gitRepos) {
        return GetGitReposResponse.builder()
                .gitRepos(gitRepos.stream()
                        .map(repo -> GetGitReposResponse.GitRepo.builder()
                                .id(repo.getId())
                                .name(repo.getName())
                                .build())
                        .toList())
                .build();
    }
}
