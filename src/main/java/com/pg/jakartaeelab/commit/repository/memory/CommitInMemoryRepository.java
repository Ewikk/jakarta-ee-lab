package com.pg.jakartaeelab.commit.repository.memory;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.commit.repository.api.CommitRepository;
import com.pg.jakartaeelab.datastore.component.DataStore;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CommitInMemoryRepository implements CommitRepository {
    private final DataStore dataStore;

    public CommitInMemoryRepository(DataStore store) {
        this.dataStore = store;
    }

    @Override
    public Optional<Commit> findByIdAndUser(UUID id, User user) {
        return dataStore.findAllCommits().stream().filter(commit -> commit.getAuthor().equals(user)).filter(commit -> commit.getId().equals(id)).findFirst();
    }

    @Override
    public List<Commit> findAllByUser(User user) {
        return dataStore.findAllCommits().stream().filter(commit -> user.equals(commit.getAuthor())).collect(Collectors.toList());
    }

    @Override
    public List<Commit> findAllByGitRepository(GitRepository gitRepo) {
        return dataStore.findAllCommits().stream().filter(commit -> gitRepo.equals(commit.getGitRepository())).collect(Collectors.toList());
    }

    @Override
    public Optional<Commit> find(UUID id) {
        return dataStore.findAllCommits().stream().filter(commit -> commit.getId().equals(id)).findFirst();
    }

    @Override
    public List<Commit> findAll() {
        return dataStore.findAllCommits();
    }

    @Override
    public void create(Commit entity) {
        dataStore.createCommit(entity);
    }

    @Override
    public void delete(Commit entity) {
        dataStore.deleteCommit(entity.getId());
    }

    @Override
    public void update(Commit entity) {
        dataStore.updateCommit(entity);
    }
}
