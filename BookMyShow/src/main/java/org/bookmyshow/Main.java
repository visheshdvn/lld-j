package org.bookmyshow;

import org.bookmyshow.enums.PaymentType;
import org.bookmyshow.model.*;
import org.bookmyshow.repository.BookingRepository;
import org.bookmyshow.repository.MovieRepository;
import org.bookmyshow.repository.ShowRepository;
import org.bookmyshow.repository.TheatreRepository;
import org.bookmyshow.service.BookingService;
import org.bookmyshow.service.MovieService;
import org.bookmyshow.service.ShowService;
import org.bookmyshow.service.TheatreService;
import org.bookmyshow.strategy.locking.InMemoryLockProvider;
import org.bookmyshow.strategy.locking.LockProvider;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws InterruptedException {
//        repo
        TheatreRepository theatreRepo = new TheatreRepository();
        MovieRepository movieRepo = new MovieRepository();
        ShowRepository showRepo = new ShowRepository();
        BookingRepository bookingRepo = new BookingRepository();

//        lock provider
        LockProvider lockProvider = new InMemoryLockProvider();

//        services
        TheatreService theatreService = new TheatreService(theatreRepo);
        MovieService movieService = new MovieService(movieRepo);
        ShowService showService = new ShowService(showRepo, theatreRepo, movieRepo);
        BookingService bookingService = new BookingService(lockProvider, bookingRepo);

//        Create theater and screens
        Theatre pvr = theatreService.createTheatre("t1", "PVR");
        Screen screen1 = new Screen("s1");
        theatreService.addScreen("t1", screen1);

//        Regular Seats
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-2", 150));
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-3", 150));
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-4", 150));
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-5", 150));
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-6", 150));
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-7", 150));
        theatreService.addSeat("t1", "s1", new RegularSeat("s1-8", 150));

//        recliner seat
        theatreService.addSeat("t1", "s1", new ReclinerSeat("s1-9", 300));
        theatreService.addSeat("t1", "s1", new ReclinerSeat("s1-10", 30));

//        Create movie
        Movie interstellar = movieService.createMovie("m1", "Interstellar", 120);

//        schedule show
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JULY, 17, 18, 30, 0);
        Date showStartTime = calendar.getTime();

        Show show1 = showService.createShow("show1", "m1", "t1", "s1", showStartTime);


        IO.println(" === Demo 1: Search shows for movie ===");
        List<Show> shows = showService.getShowsByMovieTitle("Interstellar");
        shows.forEach(System.out::println);

        IO.println(" \n=== Demo 2: 1 user books seats ===");
        Booking booking1 = bookingService.createBooking("user1", show1, List.of("s1-1", "s1-2"));
        bookingService.confirmBooking(booking1, PaymentType.CARD);

        IO.println(" \n=== Demo 3: 2 users try to book overlapping seats concurrently ===");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                Booking b = bookingService.createBooking("user2", show1, List.of("s1-3", "s1-4"));
                Thread.sleep(1000);
                bookingService.confirmBooking(b, PaymentType.CARD);
            } catch (Exception e) {
                IO.println("user2 failed: " + e.getMessage());
            }
        });

        executor.submit(() -> {
            try {
                Booking b = bookingService.createBooking("user3", show1, List.of("s1-4", "s1-5"));
                Thread.sleep(1000);
                bookingService.confirmBooking(b, PaymentType.UPI);
            } catch (Exception e) {
                IO.println("user3 failed: " + e.getMessage());
            }
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        IO.println(" \n=== Demo 4: Booking expires after TTL ===");
        try {
            Booking b = bookingService.createBooking("user4", show1, List.of("s1-6", "s1-7"));
            IO.println("user4 created booking but didn't pay");
//            TTL = 5sec

            Thread.sleep(6000);

            IO.println("user5 trying to book the same seat after TTL");
            Booking b2 = bookingService.createBooking("user5", show1, List.of("s1-6", "s1-7"));
            IO.println("user5 booking created");

            try {
                IO.println("user4 trying to pay after TTL");
                bookingService.confirmBooking(b, PaymentType.UPI);
                IO.println("user4 payment successful"); // this shouldn't print
            } catch (Exception e) {
                IO.println("user4 payment failed: " + e.getMessage());
            }

            try {
                IO.println("user5 confirming payment...");
                bookingService.confirmBooking(b2, PaymentType.UPI);
                IO.println("user5 payment successful");
            } catch (Exception e) {
                IO.println("user5 payment failed: " + e.getMessage());
            }
        } catch (Exception e) {
            IO.println("Demo4 failed: " + e.getMessage());
        }
    }
}
