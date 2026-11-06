package org.bookmyshow.strategy.payment;

import org.bookmyshow.model.Booking;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        IO.println("Paid " + booking.getAmount() + " via card for booking ID: " + booking.getBookingId());
        return true;
    }
}
