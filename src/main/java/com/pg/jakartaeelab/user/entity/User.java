package com.pg.jakartaeelab.user.entity;

import com.pg.jakartaeelab.commit.entity.Commit;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User implements Serializable {

    private UUID id;
    private String login;
    private LocalDate dateOfBirth;
    private List<Commit> commits;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] avatar;
}
