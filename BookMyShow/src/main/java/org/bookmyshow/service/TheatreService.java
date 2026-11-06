package org.bookmyshow.service;

import lombok.AllArgsConstructor;
import org.bookmyshow.model.Screen;
import org.bookmyshow.model.Seat;
import org.bookmyshow.model.Theatre;
import org.bookmyshow.repository.TheatreRepository;

@AllArgsConstructor
public class TheatreService {
    private final TheatreRepository theatreRepo;

    public Theatre createTheatre(String id, String name) {
        Theatre theatre = new Theatre(id, name);
        theatreRepo.save(theatre);
        return theatre;
    }

    public Theatre getTheatre(String id) {
        return theatreRepo.get(id);
    }

    public void addSeat(String theatreId, String screenId, Seat seat) {
        Screen screen = theatreRepo.get(theatreId).getScreen(screenId);
        screen.addSeat(seat);
    }

    public void addScreen(String theatreId, Screen screen) {
        theatreRepo.get(theatreId).addScreen(screen);
    }
}
