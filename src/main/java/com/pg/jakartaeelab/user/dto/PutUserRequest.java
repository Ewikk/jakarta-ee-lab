package com.pg.jakartaeelab.user.dto;

import com.pg.jakartaeelab.commit.entity.Commit;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PutUserRequest {
    private UUID id;
    private String login;
    private LocalDate dateOfBirth;

    @Singular
    private List<Commit> commits;
}
