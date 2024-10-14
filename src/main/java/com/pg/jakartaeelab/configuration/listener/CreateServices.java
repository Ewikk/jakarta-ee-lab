package com.pg.jakartaeelab.configuration.listener;

import com.pg.jakartaeelab.datastore.component.DataStore;
import com.pg.jakartaeelab.user.repository.api.UserRepository;
import com.pg.jakartaeelab.user.repository.memory.UserInMemoryRepository;
import com.pg.jakartaeelab.user.service.UserService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateServices implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserInMemoryRepository(dataSource);

        event.getServletContext().setAttribute("userService", new UserService(userRepository));
    }
}
