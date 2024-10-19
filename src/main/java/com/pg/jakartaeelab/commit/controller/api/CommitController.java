package com.pg.jakartaeelab.commit.controller.api;

import com.pg.jakartaeelab.commit.dto.GetCommitResponse;
import com.pg.jakartaeelab.commit.dto.GetCommitsResponse;
import com.pg.jakartaeelab.commit.dto.PatchCommitRequest;
import com.pg.jakartaeelab.commit.dto.PutCommitRequest;

import java.util.UUID;

public interface CommitController {
    GetCommitsResponse getCommits();

    GetCommitsResponse getRepoCommits(UUID uuid);

    GetCommitsResponse getUserCommits(UUID uuid);

    GetCommitResponse getCommit(UUID uuid);

    void putCommit(UUID id, PutCommitRequest request);

    void patchCommit(UUID id, PatchCommitRequest request);

    void deleteCommit(UUID id);
}
