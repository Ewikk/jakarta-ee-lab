package com.pg.jakartaeelab.commit.repository;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.repository.api.Repository;
import com.pg.jakartaeelab.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommitRepository extends Repository<Commit, UUID>{
    Optional<Commit> findByIdAndUser(String id, User user);

    List<Commit> findAllByUser(User user);

}
