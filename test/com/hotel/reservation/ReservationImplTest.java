package com.hotel.reservation;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ReservationImplTest {
	
	Reservation reservation;
	
	@Rule
	public ErrorCollector collector=new ErrorCollector();

	@Before
	public void setUp() throws Exception {
		reservation=new ReservationImpl();
	}

	@Test
	public void GivenDateRangeWhenValidThenTrue() {
		//Arrange
		List<BookingDateRange> orders = Arrays.asList(new BookingDateRange(0, 0), new BookingDateRange(8, 19),
				new BookingDateRange(365, 365));
		//Act
		boolean result1=reservation.isValid(orders.get(0));
		boolean result2=reservation.isValid(orders.get(1));
		boolean result3=reservation.isValid(orders.get(2));
		//Assert
		collector.checkThat(true, CoreMatchers.is(result1));
		collector.checkThat(true, CoreMatchers.is(result2));
		collector.checkThat(true, CoreMatchers.is(result3));
	}
	@Test
	public void GivenDateRangeWhenInValidThenFalse() {
		//Arrange
		List<BookingDateRange> orders = Arrays.asList(new BookingDateRange(9, 8), new BookingDateRange(2, 3000),
				new BookingDateRange(-1, 55));
		//Act
		boolean result1=reservation.isValid(orders.get(0));
		boolean result2=reservation.isValid(orders.get(1));
		boolean result3=reservation.isValid(orders.get(2));
		//Assert
		collector.checkThat("Start should not greater than End",false, CoreMatchers.is(result1));
		collector.checkThat("Range beyond the limit",false, CoreMatchers.is(result2));
		collector.checkThat("Range beyond the limit",false, CoreMatchers.is(result3));
	}

	@Test
	public void GivenDateRangeWhenAfterDeclinedThenAccepted() {
		//Arrange
		List<BookingDateRange> orders = Arrays.asList(new BookingDateRange(2, 21),new BookingDateRange(0, 0), new BookingDateRange(-2, 4),
				new BookingDateRange(365, 365));
		//Act
		boolean result1=reservation.reserveRoom(orders.get(0));
		boolean result2=reservation.reserveRoom(orders.get(1));
		boolean result3=reservation.reserveRoom(orders.get(2));
		boolean result4=reservation.reserveRoom(orders.get(3));
		//Assert
		collector.checkThat("Accepted",true, CoreMatchers.is(result1));
		collector.checkThat("Accepted",true, CoreMatchers.is(result2));
		collector.checkThat("Declined",false, CoreMatchers.is(result3));
		collector.checkThat("Aceepted",true, CoreMatchers.is(result4));
	}
	
	@Test
	public void GivenDateRangeWhenRoomsOccupiedThenDeclined() {
		//Arrange
		List<BookingDateRange> orders = IntStream.range(0, Hotel.size+1).mapToObj(i->new BookingDateRange(2, 4)).collect(Collectors.toList());
		//Act
		IntStream.range(0, Hotel.size).forEach(i->reservation.reserveRoom(orders.get(i))); ;
		boolean result=reservation.reserveRoom(orders.get(Hotel.size));
		//Assert
		collector.checkThat("Declined",false, CoreMatchers.is(result));
	}

	
}
