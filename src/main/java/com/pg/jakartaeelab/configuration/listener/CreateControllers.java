package com.pg.jakartaeelab.configuration.listener;

import com.pg.jakartaeelab.component.DtoFunctionFactory;
import com.pg.jakartaeelab.user.controller.simple.UserSimpleController;
import com.pg.jakartaeelab.user.service.UserService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateControllers implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");
        event.getServletContext().setAttribute("userController", new UserSimpleController(userService, new DtoFunctionFactory()));
    }
}
