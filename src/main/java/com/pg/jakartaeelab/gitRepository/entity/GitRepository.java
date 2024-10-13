package com.pg.jakartaeelab.gitRepository.entity;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.user.entity.User;

import java.time.LocalDate;
import java.util.List;

public class GitRepository {
    private String name;
    private LocalDate creationDate;
    private RepositoryVisibility visibility;
    private List<Commit> commits;
    private User owner;

}

enum RepositoryVisibility{
    PUBLIC, PRIVATE
}
