package com.pg.jakartaeelab.controller.servlet;

import com.pg.jakartaeelab.controller.servlet.exception.AlreadyExistsException;
import com.pg.jakartaeelab.controller.servlet.exception.NotFoundException;
import com.pg.jakartaeelab.user.controller.api.UserController;
import com.pg.jakartaeelab.user.controller.simple.UserSimpleController;
import com.pg.jakartaeelab.user.dto.PatchUserRequest;
import com.pg.jakartaeelab.user.dto.PutUserRequest;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig()
public class ApiServlet extends HttpServlet {

    private UserController userController;

    private String avatarPath;

    public static final class Paths{
        public static final String API = "/api";
    }

    public static final class Patterns {
        private static final Pattern UUID = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        private static final Pattern USERS = Pattern.compile("/users");
        private static final Pattern USER = Pattern.compile("/users/(%s)".formatted(UUID.pattern()));
        private static final Pattern USER_AVATAR = Pattern.compile("/users/(%s)/avatar".formatted(UUID.pattern()));
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }



    @Override
    public void init() throws ServletException {
        super.init();
        userController = (UserSimpleController) getServletContext().getAttribute("userController");
        avatarPath = (String) getServletContext().getInitParameter("avatars-path");
        System.out.println("INIT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("GET");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USERS.pattern())) {
                try {
                    resp.getWriter().write(jsonb.toJson(userController.getUsers()));
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("application/json");
                } catch (NotFoundException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;

            } else if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                try {
                    resp.getWriter().write(jsonb.toJson(userController.getUser(uuid)));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (NotFoundException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;

            } else if (path.matches(Patterns.USER_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                resp.setContentType("image/png");
                try {
                    byte[] avatar = userController.getUserAvatar(uuid, avatarPath);
                    resp.setContentLength(avatar.length);
                    resp.getOutputStream().write(avatar);
                } catch (NotFoundException ex) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
                }
                return;

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("PUT");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USER.pattern())) {
                System.out.println("PUT USER");
                UUID uuid = extractUuid(Patterns.USER, path);
                try {
                    userController.putUser(uuid, jsonb.fromJson(req.getReader(), PutUserRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "users", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch(AlreadyExistsException e) {
                    resp.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());
                }
                return;
            } else if (path.matches(Patterns.USER_AVATAR.pattern())) {
                resp.setContentType("image/png");
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                    userController.putUserAvatar(uuid, req.getPart("avatar").getInputStream(), avatarPath);
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                } catch (AlreadyExistsException ex) {
                    resp.sendError(HttpServletResponse.SC_CONFLICT, ex.getMessage());
                } catch (NotFoundException ex) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
                }
                return;

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("DELETE");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                try {
                    userController.deleteUser(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (NotFoundException e) {
                    System.out.println("User not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            }
            if (path.matches(Patterns.USER_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                    userController.deleteUserAvatar(uuid, avatarPath);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (NotFoundException ex) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
                }
                return;

            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("PATCH");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USER.pattern())) {
                System.out.println("PATCH USER");
                UUID uuid = extractUuid(Patterns.USER, path);
                userController.patchUser(uuid, jsonb.fromJson(req.getReader(), PatchUserRequest.class));
                resp.addHeader("Location", createUrl(req, Paths.API, "users", uuid.toString()));
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }
            if (path.matches(Patterns.USER_AVATAR.pattern())){
                resp.setContentType("image/png");
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                    userController.patchUserAvatar(uuid, req.getPart("avatar").getInputStream(), avatarPath);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (NotFoundException ex) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
                }
                return;

            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }

    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        for (String path : paths) {
            builder.append("/")
                    .append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }


}