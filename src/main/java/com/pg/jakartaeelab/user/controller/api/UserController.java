package com.pg.jakartaeelab.user.controller.api;

import com.pg.jakartaeelab.user.dto.GetUserResponse;
import com.pg.jakartaeelab.user.dto.GetUsersResponse;
import com.pg.jakartaeelab.user.dto.PatchUserRequest;
import com.pg.jakartaeelab.user.dto.PutUserRequest;

import java.io.InputStream;
import java.util.UUID;

public interface UserController {
    GetUserResponse getUser(UUID id);
    GetUsersResponse getUsers();

    void putUser(UUID id, PutUserRequest request);
    void patchUser(UUID id, PatchUserRequest request);
    void deleteUser(UUID id);
    byte[] getUserAvatar(UUID id, String path);

    void putUserAvatar(UUID id, InputStream avatar, String path);
    void patchUserAvatar(UUID id, InputStream avatar, String path);

    void deleteUserAvatar(UUID id, String path);
}
