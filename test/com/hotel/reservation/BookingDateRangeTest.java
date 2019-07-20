package com.hotel.reservation;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class BookingDateRangeTest {

	BookingDateRange dateRange;
	
	@Rule
	public ErrorCollector collector=new ErrorCollector();
	
	@Before
	public void setUp() throws Exception {
		dateRange=new BookingDateRange(0, 0);
	}

	@Test
	public void testStartDate() {
		//Act
		dateRange.setStartDate(1);
		//Assert
		collector.checkThat(1, CoreMatchers.is(dateRange.getStartDate()));
	}
	
	@Test
	public void testEndDate() {
		//Act
		dateRange.setEndDate(1);
		//Assert
		collector.checkThat(1, CoreMatchers.is(dateRange.getEndDate()));
	}

}
