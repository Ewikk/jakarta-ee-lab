package com.pg.jakartaeelab.commit.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchCommitRequest {
    private String Message;
    private LocalDateTime timestamp;
    private int filesChangedCount;
}
