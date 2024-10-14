package com.pg.jakartaeelab.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserResponse {
    private UUID id;
    private String login;
    private LocalDate dateOfBirth;
}
