package com.pg.jakartaeelab.gitRepository.repository.memory;

import com.pg.jakartaeelab.datastore.component.DataStore;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.gitRepository.repository.api.GitRepoRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class GitRepoInMemoryRepository implements GitRepoRepository {

    private final DataStore dataStore;

    @Inject
    public GitRepoInMemoryRepository(DataStore store) {
        this.dataStore = store;
    }

    @Override
    public Optional<GitRepository> find(UUID id) {
        return dataStore.findAllGitRepositories().stream().filter(gitRepository -> gitRepository.getId().equals(id)).findFirst();
    }

    @Override
    public List<GitRepository> findAll() {
        return dataStore.findAllGitRepositories();
    }

    @Override
    public void create(GitRepository entity) {
        dataStore.createGitRepo(entity);
    }

    @Override
    public void delete(GitRepository entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    @Override
    public void update(GitRepository entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }
}
