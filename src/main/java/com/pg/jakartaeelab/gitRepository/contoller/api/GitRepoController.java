package com.pg.jakartaeelab.gitRepository.contoller.api;

import com.pg.jakartaeelab.gitRepository.dto.GitRepoResponse;
import com.pg.jakartaeelab.gitRepository.dto.GitReposResponse;
import com.pg.jakartaeelab.gitRepository.dto.PatchGitRepoRequest;
import com.pg.jakartaeelab.gitRepository.dto.PutGitRepoRequest;

import java.util.UUID;

public interface GitRepoController {

    GitReposResponse getGitRepos();

    GitRepoResponse getGitRepo(UUID id);

    void putGitRepo(UUID id, PutGitRepoRequest request);

    void patchGitRepo(UUID id, PatchGitRepoRequest request);

    void delete(UUID id);
}
