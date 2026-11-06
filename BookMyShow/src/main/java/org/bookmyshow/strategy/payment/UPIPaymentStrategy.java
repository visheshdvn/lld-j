package org.bookmyshow.strategy.payment;

import org.bookmyshow.model.Booking;

public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        IO.println("Paid " + booking.getAmount() + " via UPI for booking ID: " + booking.getBookingId());
        return true;
    }
}
