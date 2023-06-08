package com.example.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Name Lastname", "user@example.com", "password123");
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals("Name Lastname", user.getName());
        Assertions.assertEquals("password123", user.getPassword());
        Assertions.assertEquals("user@example.com", user.getEmail());
    }

    @Test
    public void testSetters() {
        user.setName("Robert Lastname");
        Assertions.assertEquals("Robert Lastname", user.getName());

        user.setPassword("new_password");
        Assertions.assertEquals("new_password", user.getPassword());

        user.setEmail("robert@example.com");
        Assertions.assertEquals("robert@example.com", user.getEmail());
    }
}