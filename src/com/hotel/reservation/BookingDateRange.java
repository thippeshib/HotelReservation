package com.hotel.reservation;


public class BookingDateRange {
	//@min(0)@max(365)
	private long startDate;
	//@min(0)@max(365)
	private long endDate;
	
	
	
	public BookingDateRange(long startDate, long endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "BookingDaysRange [startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
