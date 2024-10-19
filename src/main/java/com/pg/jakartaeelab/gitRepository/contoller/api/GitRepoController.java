package com.pg.jakartaeelab.gitRepository.contoller.api;

import com.pg.jakartaeelab.gitRepository.dto.GetGitRepoResponse;
import com.pg.jakartaeelab.gitRepository.dto.GetGitReposResponse;

import java.util.UUID;

public interface GitRepoController {

    GetGitReposResponse getGitRepos();

    GetGitRepoResponse getGitRepo(UUID id);
}
