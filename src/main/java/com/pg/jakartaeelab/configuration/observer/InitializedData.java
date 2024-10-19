package com.pg.jakartaeelab.configuration.observer;


import com.pg.jakartaeelab.commit.entity.Commit;
import com.pg.jakartaeelab.commit.service.CommitService;
import com.pg.jakartaeelab.gitRepository.entity.GitRepository;
import com.pg.jakartaeelab.gitRepository.entity.RepositoryVisibility;
import com.pg.jakartaeelab.gitRepository.service.GitRepoService;
import com.pg.jakartaeelab.user.entity.User;
import com.pg.jakartaeelab.user.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.jws.soap.SOAPBinding;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
@ApplicationScoped
public class InitializedData implements ServletContextListener {
    private final UserService userService;
    private final CommitService commitService;
    private final GitRepoService repoService;

    private final RequestContextController requestContextController;

//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        userService = (UserService) event.getServletContext().getAttribute("userService");
//        init();
//    }

    @Inject
    public InitializedData(
            UserService userService,
            CommitService commitService,
            GitRepoService gitRepoService,
            RequestContextController requestContextController
    ){
        this.userService = userService;
        this.commitService = commitService;
        this.repoService = gitRepoService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }
    @SneakyThrows
    private void init() {
        requestContextController.activate();

        User user1 = User.builder()
                .id(UUID.fromString("c195b6a2-5966-4dd0-a790-5b160d34179f"))
                .login("Ewik")
//                .avatar(getResourceAsByteArray("/avatars/ewik.jpg"))
                .dateOfBirth(LocalDate.of(1990, 2, 2))
                .build();

        User user2 = User.builder()
                .id(UUID.fromString("f08ce4de-c57e-4f6e-a55f-7ce6a15e871d"))
                .login("Redix")
//                .avatar(getResourceAsByteArray("/avatars/redix.jpg"))
                .dateOfBirth(LocalDate.of(2001, 3, 13))
                .build();

        User user3 = User.builder()
                .id(UUID.fromString("96764b32-7085-49b2-aa46-7ad686e8efbe"))
                .login("Wise")
//                .avatar(getResourceAsByteArray("/avatars/wise.jpg"))
                .dateOfBirth(LocalDate.of(1963, 6, 22))
                .build();

        User user4 = User.builder()
                .id(UUID.fromString("a9525063-6a04-454b-9185-9a4c290a88b8"))
                .login("Perry")
//                .avatar(getResourceAsByteArray("/avatars/perry.jpg"))
                .dateOfBirth(LocalDate.of(2007, 12, 12))
                .build();

        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        GitRepository gitRepo1 = GitRepository.builder()
                .id(UUID.fromString("6411cea1-4e09-43c8-ad16-91215022025d"))
                .creationDate(LocalDate.of(2024, 1, 1))
                .owner(user1)
                .visibility(RepositoryVisibility.PUBLIC)
                .name("jakarta-ee-lab")
                .build();

        GitRepository gitRepo2 = GitRepository.builder()
                .id(UUID.fromString("6411cea1-4e09-43c8-ad16-91215022025e"))
                .creationDate(LocalDate.of(2023, 4, 15))
                .owner(user1)
                .visibility(RepositoryVisibility.PRIVATE)
                .name("sportex-group-project")
                .build();

        GitRepository gitRepo3 = GitRepository.builder()
                .id(UUID.fromString("6411cea1-4e09-43c8-ad16-91215022025f"))
                .creationDate(LocalDate.of(2022, 11, 15))
                .owner(user2)
                .visibility(RepositoryVisibility.PRIVATE)
                .name("TPikies-ships")
                .build();

        GitRepository gitRepo4 = GitRepository.builder()
                .id(UUID.fromString("6411cea1-4e09-43c8-ad16-912150220250"))
                .creationDate(LocalDate.of(2020, 2, 2))
                .owner(user3)
                .visibility(RepositoryVisibility.PUBLIC)
                .name("open-ai-blockchain-project")
                .build();

        Commit commit1 = Commit.builder()
                .id(UUID.fromString("7411cea1-4e09-43c8-ad16-912150220250"))
                .author(user1)
                .timestamp(LocalDateTime.of(2024, 2, 3, 12, 12))
                .message("code refactor")
                .filesChangedCount(3)
                .gitRepository(gitRepo1)
                .build();
        Commit commit2 = Commit.builder()
                .id(UUID.fromString("8411cea1-4e09-43c8-ad16-912150220250"))
                .author(user2)
                .timestamp(LocalDateTime.of(2024, 2, 3, 14, 1))
                .message("gitignore updated")
                .filesChangedCount(3)
                .gitRepository(gitRepo1)
                .build();

        Commit commit3 = Commit.builder()
                .id(UUID.fromString("9411cea1-4e09-43c8-ad16-912150220250"))
                .author(user3)
                .timestamp(LocalDateTime.of(2024, 2, 3, 18, 3))
                .message("controller null ref fix")
                .filesChangedCount(3)
                .gitRepository(gitRepo1)
                .build();

        Commit commit4 = Commit.builder()
                .id(UUID.fromString("A411cea1-4e09-43c8-ad16-912150220250"))
                .author(user2)
                .timestamp(LocalDateTime.of(2023, 4, 15, 10, 12))
                .message("repo init")
                .filesChangedCount(3)
                .gitRepository(gitRepo2)
                .build();

        //gitRepo1.setCommits(Arrays.asList(commit1, commit2, commit3));
        //gitRepo2.setCommits(Arrays.asList(commit4));

        repoService.create(gitRepo1);
        repoService.create(gitRepo2);
        repoService.create(gitRepo3);
        repoService.create(gitRepo4);

        commitService.create(commit1);
        commitService.create(commit2);
        commitService.create(commit3);
        commitService.create(commit4);

        requestContextController.deactivate();
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
