package com.pg.jakartaeelab.commit.controller.simple;

import com.pg.jakartaeelab.commit.controller.api.CommitController;
import com.pg.jakartaeelab.commit.dto.GetCommitResponse;
import com.pg.jakartaeelab.commit.dto.GetCommitsResponse;
import com.pg.jakartaeelab.commit.dto.PatchCommitRequest;
import com.pg.jakartaeelab.commit.dto.PutCommitRequest;
import com.pg.jakartaeelab.commit.service.CommitService;
import com.pg.jakartaeelab.component.DtoFunctionFactory;
import com.pg.jakartaeelab.controller.servlet.exception.BadRequestException;
import com.pg.jakartaeelab.controller.servlet.exception.NotFoundException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@RequestScoped
public class CommitSimpleController implements CommitController {
    private final CommitService service;
    private final DtoFunctionFactory factory;

    @Inject
    public CommitSimpleController(CommitService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetCommitsResponse getCommits() {
        return factory.commitsToResponse().apply(service.findAll());
    }

    @Override
    public GetCommitsResponse getRepoCommits(UUID uuid) {
        return service.findAllByRepo(uuid).map(factory.commitsToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetCommitsResponse getUserCommits(UUID uuid) {
        return service.findAllByUser(uuid).map(factory.commitsToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetCommitResponse getCommit(UUID uuid) {
        return service.find(uuid).map(factory.commitToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public void putCommit(UUID id, PutCommitRequest request) {
        try {
            service.create(factory.requestToCommit().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchCommit(UUID id, PatchCommitRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateCommitWithRequestFunction().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteCommit(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                });

    }
}
