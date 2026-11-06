package org.bookmyshow.model;

import org.bookmyshow.enums.SeatType;

public class ReclinerSeat extends Seat {
    public ReclinerSeat(String id, double price) {
        super(id, price);
    }

    public SeatType getType() {
        return SeatType.RECLINER;
    }
}
