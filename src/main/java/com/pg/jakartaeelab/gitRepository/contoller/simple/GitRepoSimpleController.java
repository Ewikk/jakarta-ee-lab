package com.pg.jakartaeelab.gitRepository.contoller.simple;

import com.pg.jakartaeelab.component.DtoFunctionFactory;
import com.pg.jakartaeelab.gitRepository.contoller.api.GitRepoController;
import com.pg.jakartaeelab.gitRepository.dto.GetGitRepoResponse;
import com.pg.jakartaeelab.gitRepository.dto.GetGitReposResponse;
import com.pg.jakartaeelab.gitRepository.service.GitRepoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@RequestScoped
public class GitRepoSimpleController implements GitRepoController {
    private final GitRepoService service;
    private final DtoFunctionFactory factory;

    @Inject
    public GitRepoSimpleController(GitRepoService service, DtoFunctionFactory factory){
        this.service = service;
        this.factory = factory;
    }
    @Override
    public GetGitReposResponse getGitRepos() {
        return null;
    }

    @Override
    public GetGitRepoResponse getGitRepo(UUID id) {
        return null;
    }
}
