package org.bookmyshow.model;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Show {
    private final String id;
    private final Movie movie;
    private final Date start;
    private final Date end;
    private final Screen screen;
    private final Theatre theatre;

    public Show(String id, Movie movie, Date start, Screen screen, Theatre theatre) {
        this.id = id;
        this.start = start;
        this.screen = screen;
        this.theatre = theatre;
        this.movie = movie;
        this.end = start;
    }

    public List<Seat> getSeats() {
        return screen.getSeats();
    }

    @Override
    public String toString() {
        return "overridden string";
    }
}
