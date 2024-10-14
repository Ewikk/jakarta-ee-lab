package com.pg.jakartaeelab.gitRepository.repository.memory;

import com.pg.jakartaeelab.datastore.component.DataStore;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.gitRepository.repository.api.GitRepositoryRepository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GitRepositoryMemoryRepository implements GitRepositoryRepository {

    private final DataStore dataStore;

    public GitRepositoryMemoryRepository(DataStore store) {
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
