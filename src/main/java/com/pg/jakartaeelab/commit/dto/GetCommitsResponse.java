package com.pg.jakartaeelab.commit.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCommitsResponse {

    @Data
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    public static class Commit{
        private UUID id;
        private String message;
    }

    private List<Commit> commits;
}
