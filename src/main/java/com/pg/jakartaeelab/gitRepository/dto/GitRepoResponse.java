package com.pg.jakartaeelab.gitRepository.dto;

import com.pg.jakartaeelab.commit.dto.GetCommitsResponse;
import com.pg.jakartaeelab.gitRepository.entity.RepositoryVisibility;
import com.pg.jakartaeelab.user.dto.GetUsersResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GitRepoResponse {
    private UUID id;
    private String name;
    private LocalDate creationDate;
    private RepositoryVisibility visibility;
    private List<GetCommitsResponse.Commit> commits;
    private GetUsersResponse.User owner;
}
