package com.pg.jakartaeelab.commit.entity;

import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.user.entity.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Commit implements Serializable {
    private UUID id;
    //private String commitId;
    private LocalDateTime timestamp;
    private String message;
    private int filesChangedCount;
    private User author;
    private GitRepository gitRepository;

}
