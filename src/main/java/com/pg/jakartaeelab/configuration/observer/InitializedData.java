package com.pg.jakartaeelab.configuration.observer;


import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;
@ApplicationScoped
public class InitializedData implements ServletContextListener {
    private final UserService userService;

//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        userService = (UserService) event.getServletContext().getAttribute("userService");
//        init();
//    }

    @Inject
    public InitializedData(
            UserService userService
    ){
        this.userService = userService;
    }

    @SneakyThrows
    private void init() {
        userService.create(User.builder()
                .id(UUID.fromString("c195b6a2-5966-4dd0-a790-5b160d34179f"))
                .login("Ewik")
//                .avatar(getResourceAsByteArray("/avatars/ewik.jpg"))
                .dateOfBirth(LocalDate.of(1990, 2, 2))
                .build());

        userService.create(User.builder()
                .id(UUID.fromString("f08ce4de-c57e-4f6e-a55f-7ce6a15e871d"))
                .login("Redix")
//                .avatar(getResourceAsByteArray("/avatars/redix.jpg"))
                .dateOfBirth(LocalDate.of(2001, 3, 13))
                .build());

        userService.create(User.builder()
                .id(UUID.fromString("96764b32-7085-49b2-aa46-7ad686e8efbe"))
                .login("Wise")
//                .avatar(getResourceAsByteArray("/avatars/wise.jpg"))
                .dateOfBirth(LocalDate.of(1963, 6, 22))
                .build());

        userService.create(User.builder()
                .id(UUID.fromString("a9525063-6a04-454b-9185-9a4c290a88b8"))
                .login("Perry")
//                .avatar(getResourceAsByteArray("/avatars/perry.jpg"))
                .dateOfBirth(LocalDate.of(2007, 12, 12))
                .build());
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }
}
