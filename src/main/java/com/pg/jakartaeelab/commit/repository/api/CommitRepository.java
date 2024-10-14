package com.pg.jakartaeelab.commit.repository.api;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.repository.api.Repository;
import com.pg.jakartaeelab.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommitRepository extends Repository<Commit, UUID>{
    Optional<Commit> findByIdAndUser(UUID id, User user);

    List<Commit> findAllByUser(User user);

    List<Commit> findAllByGitRepository(GitRepository gitRepo);

}
