package com.pg.jakartaeelab.commit.dto.function;

import com.pg.jakartaeelab.commit.dto.PatchCommitRequest;
import com.pg.jakartaeelab.commit.entity.Commit;

import java.util.function.BiFunction;

public class UpdateCommitWithRequestFunction implements BiFunction<Commit, PatchCommitRequest, Commit> {
    @Override
    public Commit apply(Commit commit, PatchCommitRequest request) {
        return Commit.builder()
                .message(request.getMessage())
                .filesChangedCount(request.getFilesChangedCount())
                .timestamp(request.getTimestamp())
                .id(commit.getId())
                .gitRepository(commit.getGitRepository())
                .author(commit.getAuthor())
                .build();
    }
}
