package org.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bookmyshow.enums.BookingStatus;
import org.bookmyshow.enums.PaymentType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Booking {
    private final String bookingId;
    private final String userId;
    private String showId;
    private final List<String> seatIds;
    private BookingStatus status;
    private PaymentType paymentType;
    private double amount;

    @Override
    public String toString() {
        return "booking temp string: " + bookingId;
    }
}
