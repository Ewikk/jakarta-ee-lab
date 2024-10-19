package com.pg.jakartaeelab.gitRepository.dto;

import com.pg.jakartaeelab.gitRepository.entity.RepositoryVisibility;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PutGitRepoRequest {
    private String name;
    private LocalDate creationDate;
    private RepositoryVisibility visibility;
    private UUID ownerId;
}
