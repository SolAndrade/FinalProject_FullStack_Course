package com.example.app.services;

import com.example.app.models.Movie;
import com.example.app.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    // Assuming you have a movieRepository to access the movie data
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int movieId) {return movieRepository.findById(movieId);
    }
}
