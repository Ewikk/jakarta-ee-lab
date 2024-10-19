package com.pg.jakartaeelab.commit.dto.function;

import com.pg.jakartaeelab.commit.dto.GetCommitResponse;
import com.pg.jakartaeelab.commit.dto.GetCommitsResponse;
import com.pg.jakartaeelab.commit.entity.Commit;

import java.util.List;
import java.util.function.Function;

public class CommitsToResponseFunction implements Function<List<Commit>, GetCommitsResponse> {

    @Override
    public GetCommitsResponse apply(List<Commit> commits) {
        return GetCommitsResponse.builder()
                .commits(commits.stream()
                        .map(commit -> GetCommitsResponse.Commit.builder()
                                .id(commit.getId())
                                .message(commit.getMessage())
                                .build())
                        .toList())
                .build();
    }
}
