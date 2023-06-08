package com.example.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {

    private Ticket ticket;
    private Movie movie;
    private User user;

    @BeforeEach
    public void setUp() {
        movie = new Movie("Title", "Description", "Genre", 120, 4.5, 12, 100, "image.jpg");
        user = new User("Name Lastname", "user@example.com", "password123");
        ticket = new Ticket(movie, user);
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals(movie, ticket.getMovie());
        Assertions.assertEquals(user, ticket.getUser());
    }

    @Test
    public void testSetters() {
        Movie newMovie = new Movie("New Title", "New Description", "New Genre", 90, 3.7, 16, 50, "new_image.jpg");
        User newUser = new User("New User", "newuser@example.com", "password123");

        ticket.setMovie(newMovie);
        Assertions.assertEquals(newMovie, ticket.getMovie());

        ticket.setUser(newUser);
        Assertions.assertEquals(newUser, ticket.getUser());
    }
}