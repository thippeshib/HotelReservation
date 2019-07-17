package com.hotel.reservation;

import java.util.Arrays;
import java.util.List;

public class HotelReservation {
	public static void main(String str[]) {
		Reservation reservation = new ReservationImpl();
		List<BookingDateRange> orders = Arrays.asList(new BookingDateRange(0, 0), new BookingDateRange(0, 2),
				new BookingDateRange(2, 4), new BookingDateRange(2, 2), new BookingDateRange(2, 3),
				new BookingDateRange(1, 1));

		orders.forEach(e -> {
			if (reservation.reserveRoom(e))
				System.out.println(e.toString()+", Result = Accepted");
			else
				System.out.println(e.toString()+", Result = Declined");

		});

	}

}
