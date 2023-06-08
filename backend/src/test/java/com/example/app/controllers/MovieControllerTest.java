package com.example.app.controllers;

import com.example.app.models.Movie;
import com.example.app.repositories.MovieRepository;
import com.example.app.services.MovieService;
import com.example.app.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MovieControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MovieRepository movieRepository;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createMovie_validMovie_movieCreated() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("This is a test movie");
        // Set other properties

        String body = objectMapper.writeValueAsString(movie);

        mockMvc.perform(post("/movies").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getMovieById_existingId_returnMovie() throws Exception {
        // Create a movie in the database
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("This is a test movie");
        // Set other properties
        Movie createdMovie = movieRepository.save(movie);

        mockMvc.perform(get("/movies/{id}", createdMovie.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Movie"))
                .andExpect(jsonPath("$.description").value("This is a test movie"))
                .andReturn();
    }

    @Test
    void getMovieById_nonExistingId_returnNotFound() throws Exception {
        mockMvc.perform(get("/movies/{id}", 123))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getAllMovies_validRequest_returnAllMovies() throws Exception {
        // Create multiple movies in the database
        Movie movie1 = new Movie();
        movie1.setTitle("Movie 1");
        movie1.setDescription("Description 1");
        // Set other properties
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie 2");
        movie2.setDescription("Description 2");
        // Set other properties
        movieRepository.save(movie2);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Movie 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[1].title").value("Movie 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andReturn();
    }

    @Test
    void updateMovie_existingId_validMovie_movieUpdated() throws Exception {
        // Create a movie in the database
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("This is a test movie");
        // Set other properties
        Movie createdMovie = movieRepository.save(movie);

        // Update the movie
        createdMovie.setTitle("Updated Movie");
        createdMovie.setDescription("This is an updated movie");
        // Update other properties

        String body = objectMapper.writeValueAsString(createdMovie);

        mockMvc.perform(put("/movies/{id}", createdMovie.getId()).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Updated Movie"))
                .andExpect(jsonPath("$.description").value("This is an updated movie"))
                .andReturn();
    }

    @Test
    void updateMovie_nonExistingId_returnNotFound() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("This is a test movie");
        // Set other properties

        String body = objectMapper.writeValueAsString(movie);

        mockMvc.perform(put("/movies/{id}", 123).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deleteMovie_existingId_movieDeleted() throws Exception {
        // Create a movie in the database
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("This is a test movie");
        // Set other properties
        Movie createdMovie = movieRepository.save(movie);

        mockMvc.perform(delete("/movies/{id}", createdMovie.getId()))
                .andExpect(status().isNoContent())
                .andReturn();

        mockMvc.perform(get("/movies/{id}", createdMovie.getId()))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deleteMovie_nonExistingId_returnNotFound() throws Exception {
        mockMvc.perform(delete("/movies/{id}", 123))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
