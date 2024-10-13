package com.pg.jakartaeelab.commit.entity;

import java.time.LocalDateTime;

public class Commit {
    private String commitId;
    private LocalDateTime timestamp;
    private String message;
    private int filesChangedCount;

}
