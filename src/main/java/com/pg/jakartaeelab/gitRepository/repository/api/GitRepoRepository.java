package com.pg.jakartaeelab.gitRepository.repository.api;

import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.repository.api.Repository;

import java.util.UUID;

public interface GitRepoRepository extends Repository<GitRepository, UUID> {
}
