package org.bookmyshow.service;

import lombok.AllArgsConstructor;
import org.bookmyshow.model.Show;
import org.bookmyshow.repository.MovieRepository;
import org.bookmyshow.repository.ShowRepository;
import org.bookmyshow.repository.TheatreRepository;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class ShowService {
    private final ShowRepository showRepo;
    private final TheatreRepository theatreRepo;
    private final MovieRepository movieRepo;

    public Show createShow(
            String showId,
            String movieId,
            String theatreId,
            String screenId,
            Date start
    ) {
        Show show = new Show(
                showId,
                movieRepo.get(movieId),
                start,
                theatreRepo.get(theatreId).getScreen(screenId),
                theatreRepo.get(theatreId)
        );

        showRepo.save(show);
        return show;
    }

    public Show getShow(String showId) {
        return showRepo.get(showId);
    }

    public List<Show> getShowsByMovieTitle(String title) {
        return showRepo.getAll().stream()
                .filter(show -> show.getMovie().getTitle().equalsIgnoreCase(title))
                .toList();
    }
}
