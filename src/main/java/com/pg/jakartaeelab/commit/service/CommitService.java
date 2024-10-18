package com.pg.jakartaeelab.commit.service;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.commit.repository.api.CommitRepository;
import com.pg.jakartaeelab.gitRepository.repository.api.GitRepoRepository;
import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.repository.api.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class CommitService {
    private final CommitRepository commitRepository;
    private final GitRepoRepository gitRepoRepository;
    private final UserRepository userRepository;

    @Inject
    public CommitService(CommitRepository commitRepository, GitRepoRepository gitRepoRepository, UserRepository userRepository) {
        this.commitRepository = commitRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.userRepository = userRepository;
    }

    public Optional<Commit> find(UUID id) {
        return commitRepository.find(id);
    }

    public Optional<Commit> find(User user, UUID id) {
        return commitRepository.findByIdAndUser(id, user);
    }

    public List<Commit> findAll() {
        return commitRepository.findAll();
    }

    public List<Commit> findAll(User user) {
        return commitRepository.findAllByUser(user);
    }

    public void create(Commit commit) {
        commitRepository.create(commit);
    }

    public void update(Commit commit) {
        commitRepository.update(commit);
    }

    public void delete(UUID uuid) {
        commitRepository.delete(commitRepository.find(uuid).orElseThrow());
    }

    public Optional<List<Commit>> findAllByRepo(UUID uuid) {
        return gitRepoRepository.find(uuid).map(commitRepository::findAllByGitRepository);
    }

    public Optional<List<Commit>> findAllByUser(UUID uuid){
        return userRepository.find(uuid).map(commitRepository::findAllByUser);
    }
}
