package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;

@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository.init();
    }

    @Test
    void testDelete() {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        assertEquals(users.size(), 1);
        assertEquals(users.iterator().next(), ADMIN);
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(10));
    }
}
