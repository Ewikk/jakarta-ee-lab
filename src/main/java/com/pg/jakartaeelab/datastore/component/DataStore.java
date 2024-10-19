package com.pg.jakartaeelab.datastore.component;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.serialization.component.CloningUtility;
import com.pg.jakartaeelab.user.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {
    private final Set<GitRepository> gitRepositories = new HashSet<>();
    private final Set<Commit> commits = new HashSet<>();
    private final Set<User> users = new HashSet<>();

    private final CloningUtility cloningUtility;

    @Inject
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    public synchronized List<GitRepository> findAllGitRepositories() {
        return gitRepositories.stream().map(cloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createGitRepo(GitRepository value) throws IllegalArgumentException {
        if (gitRepositories.stream().anyMatch(gitRepo -> gitRepo.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The git repo id \"%s\" is not unique".formatted(value.getId()));
        }
        gitRepositories.add(cloningUtility.clone(value));
    }

    public synchronized void updateGitRepo(GitRepository value) throws IllegalArgumentException {
        GitRepository entity = cloneWithRelationships(value);
        commits.stream()
                .filter(commit -> commit.getGitRepository().getId().equals(entity.getId()))
                .forEach(commit -> commit.setGitRepository(entity));
        if (gitRepositories.removeIf(repo -> repo.getId().equals(value.getId()))) {
            gitRepositories.add(entity);
        } else {
            throw new IllegalArgumentException("The git repo with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized void deleteGitRepo(UUID id) throws IllegalArgumentException {
        if (!gitRepositories.removeIf(gitRepository -> gitRepository.getId().equals(id))) {
            throw new IllegalArgumentException("The git repo with id \"%s\" does not exist".formatted(id));
        }
    }

    public synchronized List<Commit> findAllCommits() {
        return commits.stream().map(cloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createCommit(Commit value) throws IllegalArgumentException {
        if (commits.stream().anyMatch(commit -> commit.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The commit id \"%s\" is not unique".formatted(value.getId()));
        }
        Commit entity = cloneWithRelationships(value);
        commits.add(entity);
    }

    public synchronized void updateCommit(Commit value) throws IllegalArgumentException {
        Commit entity = cloneWithRelationships(value);
        if (commits.removeIf(commit -> commit.getId().equals(value.getId()))) {
            commits.add(entity);
        } else {
            throw new IllegalArgumentException("The commit with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized void deleteCommit(UUID id) throws IllegalArgumentException {
        if (!commits.removeIf(commit -> commit.getId().equals(id))) {
            throw new IllegalArgumentException("The commit with id \"%s\" does not exist".formatted(id));
        }
    }

    public synchronized List<User> findAllUsers() {
        return users.stream().map(cloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(user -> user.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        users.add(cloningUtility.clone(value));
    }

    //TO DO: solve relations, cascade deletion?
    public synchronized void deleteUser(User value) throws IllegalArgumentException {
        if (!users.removeIf(user -> user.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }


    public synchronized void updateUser(User value) throws IllegalArgumentException {
        if (users.removeIf(user -> user.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }


    private Commit cloneWithRelationships(Commit value) {
        Commit entity = cloningUtility.clone(value);

        if (entity.getAuthor() != null) {
            entity.setAuthor(users.stream().filter(user -> user.getId().equals(value.getAuthor().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getAuthor().getId()))));
        }

        if (entity.getGitRepository() != null) {
            entity.setGitRepository(gitRepositories.stream().filter(profession -> profession.getId().equals(value.getGitRepository().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("No git repo with id \"%s\".".formatted(value.getGitRepository().getId()))));
        }

        return entity;
    }

    private GitRepository cloneWithRelationships(GitRepository value) {
        GitRepository entity = cloningUtility.clone(value);

        if (entity.getOwner() != null) {
            entity.setOwner(users.stream().filter(user -> user.getId().equals(value.getOwner().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getOwner().getId()))));
        }

        if (entity.getCommits() != null) {
            entity.setCommits(commits.stream().filter(commit -> commit.getId().equals(value.getId())).toList());
        }

        return entity;
    }
}
