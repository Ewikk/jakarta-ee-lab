package com.pg.jakartaeelab.gitRepository.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetGitReposResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GitRepo{
        private UUID id;
        private String name;
    }

    private List<GitRepo> gitRepos;
}
