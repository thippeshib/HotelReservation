package com.hotel.reservation;

public interface Reservation {
	boolean reserveRoom(BookingDateRange dateRange);

	boolean isValid(BookingDateRange dateRange);

}
