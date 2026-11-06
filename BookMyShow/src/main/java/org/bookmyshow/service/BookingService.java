package org.bookmyshow.service;

import lombok.AllArgsConstructor;
import org.bookmyshow.enums.BookingStatus;
import org.bookmyshow.enums.PaymentType;
import org.bookmyshow.exception.SeatNotAvailableException;
import org.bookmyshow.factory.PaymentStrategyFactory;
import org.bookmyshow.model.Booking;
import org.bookmyshow.model.Seat;
import org.bookmyshow.model.Show;
import org.bookmyshow.repository.BookingRepository;
import org.bookmyshow.strategy.locking.LockProvider;
import org.bookmyshow.strategy.payment.PaymentStrategy;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class BookingService {
    private final LockProvider lockProvider;
    private final BookingRepository bookingRepo;

    private static final long TTL = 5000L;

    public Booking createBooking(String userId, Show show, List<String> seatIds) {

        for (String seatId : seatIds) {
            String key = show.getId() + ":" + seatId;
            if(!lockProvider.tryLock(key, userId, TTL)) {
                throw new SeatNotAvailableException("Seat " + seatId + " is temporarily unavailable");
            }
        }

        double totalPrice = 0.0;
        for (Seat seat : show.getSeats()) {
            if(seatIds.contains(seat.getId())) {
                totalPrice += seat.getPrice();
            }
        }

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                userId,
                show.getId(),
                seatIds,
                BookingStatus.CREATED,
                null,
                totalPrice
        );

        bookingRepo.save(booking);
        IO.println("Booking created by user " + userId  + " : " + booking.getBookingId());
        return booking;
    }

    public void confirmBooking(Booking booking, PaymentType paymentType) {
        if(booking.getStatus() != BookingStatus.CREATED) {
            throw new IllegalStateException("Booking is not in a valid state for confirmation");
        }

        for (String seatId : booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;
            if(lockProvider.isLockExpired(key) || !lockProvider.isLockedBy(key, booking.getUserId())) {
                throw new SeatNotAvailableException("Seat " + seatId + " is temporarily unavailable");
            }
        }

        booking.setPaymentType(paymentType);
        PaymentStrategy strategy = PaymentStrategyFactory.getStrategy(paymentType);
        strategy.pay(booking);

        for (String seatId: booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;
            lockProvider.unlock(key);
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        IO.println("Booking confirmed: " + booking.getBookingId());
    }
}
