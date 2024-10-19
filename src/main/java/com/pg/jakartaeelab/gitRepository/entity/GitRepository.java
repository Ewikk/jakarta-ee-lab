package com.pg.jakartaeelab.gitRepository.entity;

import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.user.entity.User;
import jakarta.persistence.Access;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GitRepository implements Serializable {
    private UUID id;
    private String name;
    private LocalDate creationDate;
    private RepositoryVisibility visibility;
    private List<Commit> commits;
    private User owner;

}

