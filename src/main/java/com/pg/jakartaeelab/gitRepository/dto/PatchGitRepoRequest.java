package com.pg.jakartaeelab.gitRepository.dto;

import com.pg.jakartaeelab.gitRepository.entity.RepositoryVisibility;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchGitRepoRequest {
    private String name;
    private RepositoryVisibility visibility;
}
