package com.pg.jakartaeelab.commit.dto;

import com.pg.jakartaeelab.user.dto.GetUsersResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCommitResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GitRepository {
        private UUID uuid;

        private String name;
    }


    private UUID id;

    private LocalDateTime timestamp;

    private String message;
    private int filesChangedCount;
    private GetUsersResponse.User author;
    private GitRepository gitRepository;
}
