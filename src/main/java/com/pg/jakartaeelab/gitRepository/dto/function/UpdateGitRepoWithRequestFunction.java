package com.pg.jakartaeelab.gitRepository.dto.function;

import com.pg.jakartaeelab.gitRepository.dto.PatchGitRepoRequest;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;

import java.util.function.BiFunction;

public class UpdateGitRepoWithRequestFunction implements BiFunction<GitRepository, PatchGitRepoRequest, GitRepository> {
    @Override
    public GitRepository apply(GitRepository gitRepository, PatchGitRepoRequest patchGitRepoRequest) {
        return GitRepository.builder()
                .id(gitRepository.getId())
                .name(patchGitRepoRequest.getName())
                .visibility(patchGitRepoRequest.getVisibility())
                .creationDate(gitRepository.getCreationDate())
                .owner(gitRepository.getOwner())
                .build();
    }
}
