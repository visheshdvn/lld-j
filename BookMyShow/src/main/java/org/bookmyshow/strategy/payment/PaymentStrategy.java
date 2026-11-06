package org.bookmyshow.strategy.payment;

import org.bookmyshow.model.Booking;

public interface PaymentStrategy {
    boolean pay(Booking booking);
}
