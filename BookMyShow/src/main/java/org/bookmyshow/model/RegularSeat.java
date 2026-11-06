package org.bookmyshow.model;

import org.bookmyshow.enums.SeatType;

public class RegularSeat extends Seat {
    public RegularSeat(String id, double price) {
        super(id, price);
    }

    public SeatType getType() {
        return SeatType.REGULAR;
    }
}
