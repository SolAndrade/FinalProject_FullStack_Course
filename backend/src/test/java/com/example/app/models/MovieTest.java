package com.example.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {

    private Movie movie;

    @BeforeEach
    public void setUp() {
        movie = new Movie("Title", "Description", "Genre", 120, 4.5, 12, 100, "image.jpg");
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals("Title", movie.getTitle());
        Assertions.assertEquals("Description", movie.getDescription());
        Assertions.assertEquals("Genre", movie.getGenre());
        Assertions.assertEquals(120, movie.getDuration());
        Assertions.assertEquals(4.5, movie.getRating(), 0.001);
        Assertions.assertEquals(12, movie.getAgeMin());
        Assertions.assertEquals(100, movie.getTickets());
        Assertions.assertEquals("image.jpg", movie.getImgUrl());
    }

    @Test
    public void testSetters() {
        movie.setTitle("New Title");
        Assertions.assertEquals("New Title", movie.getTitle());

        movie.setDescription("New Description");
        Assertions.assertEquals("New Description", movie.getDescription());

        movie.setGenre("New Genre");
        Assertions.assertEquals("New Genre", movie.getGenre());

        movie.setDuration(90);
        Assertions.assertEquals(90, movie.getDuration());

        movie.setRating(3.7);
        Assertions.assertEquals(3.7, movie.getRating(), 0.001);

        movie.setAgeMin(16);
        Assertions.assertEquals(16, movie.getAgeMin());

        movie.setTickets(50);
        Assertions.assertEquals(50, movie.getTickets());

        movie.setImgUrl("new_image.jpg");
        Assertions.assertEquals("new_image.jpg", movie.getImgUrl());
    }
}