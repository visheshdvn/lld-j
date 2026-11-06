package org.bookmyshow.service;

import lombok.AllArgsConstructor;
import org.bookmyshow.model.Movie;
import org.bookmyshow.repository.MovieRepository;

@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepo;

    public Movie createMovie(String id, String title, int durationInMins) {
        Movie movie = new Movie(id, title, durationInMins);
        movieRepo.save(movie);
        return movie;
    }

    public Movie getMovie(String id) {
        return movieRepo.get(id);
    }
}
