package com.pg.jakartaeelab.user.entity;

import com.pg.jakartaeelab.commit.entity.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String login;
    private LocalDate dateOfBirth;
    private List<Commit> commits;
}
