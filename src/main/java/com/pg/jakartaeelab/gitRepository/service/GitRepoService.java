package com.pg.jakartaeelab.gitRepository.service;

import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.gitRepository.repository.api.GitRepoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class GitRepoService {
    private final GitRepoRepository repository;

    @Inject
    public GitRepoService(GitRepoRepository repository) {
        this.repository = repository;
    }

    public Optional<GitRepository> find(UUID uuid) {
        return repository.find(uuid);
    }

    public List<GitRepository> findAll() {
        return repository.findAll();
    }

    public void create(GitRepository gitRepo) {
        repository.create(gitRepo);
    }



}
