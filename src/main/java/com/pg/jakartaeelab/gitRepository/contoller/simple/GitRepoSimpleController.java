package com.pg.jakartaeelab.gitRepository.contoller.simple;

import com.pg.jakartaeelab.component.DtoFunctionFactory;
import com.pg.jakartaeelab.controller.servlet.exception.AlreadyExistsException;
import com.pg.jakartaeelab.controller.servlet.exception.BadRequestException;
import com.pg.jakartaeelab.controller.servlet.exception.NotFoundException;
import com.pg.jakartaeelab.gitRepository.contoller.api.GitRepoController;
import com.pg.jakartaeelab.gitRepository.dto.GitRepoResponse;
import com.pg.jakartaeelab.gitRepository.dto.GitReposResponse;
import com.pg.jakartaeelab.gitRepository.dto.PatchGitRepoRequest;
import com.pg.jakartaeelab.gitRepository.dto.PutGitRepoRequest;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.gitRepository.service.GitRepoService;
import com.pg.jakartaeelab.user.entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class GitRepoSimpleController implements GitRepoController {
    private final GitRepoService service;
    private final DtoFunctionFactory factory;

    @Inject
    public GitRepoSimpleController(GitRepoService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GitReposResponse getGitRepos() {
        return factory.reposToResponse().apply(service.findAll());
    }

    @Override
    public GitRepoResponse getGitRepo(UUID id) {
        return service.find(id).map(factory.repoToResponse()).orElseThrow(NotFoundException::new);

    }

    @Override
    public void putGitRepo(UUID id, PutGitRepoRequest request) {
        Optional<GitRepository> repo = service.find(id);
        if (repo.isPresent()) {
            throw new AlreadyExistsException("Git repository with id " + id + " already exists");
        }
        try {
            service.create(factory.requestToGitRepo().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }
    }

    @Override
    public void patchGitRepo(UUID id, PatchGitRepoRequest request) {
        service.find(id).ifPresentOrElse(repo -> service.update(factory.updateGitRepoWithRequest().apply(repo, request)), () -> {
            throw new NotFoundException();
        });
    }

    @Override
    public void delete(UUID id) {
        service.find(id).ifPresentOrElse(service::delete, () -> {
            throw new NotFoundException();
        });
    }
}
