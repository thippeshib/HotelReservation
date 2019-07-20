package com.hotel.reservation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReservationImpl implements Reservation {

	Map<Integer, List<BookingDateRange>> map = new HashMap();

	@Override
	public boolean reserveRoom(BookingDateRange dateRange) {
		if (isValid(dateRange)) {
			if (map.size() == 0) {
				List<BookingDateRange> newList = new LinkedList<BookingDateRange>();
				newList.add(dateRange);
				map.put(0, newList);
				return true;
			} else {
				for (int i = 0; i < map.size(); i++) {
					List<BookingDateRange> list = map.get(i);
					boolean exist = true;
					for (int j = 0; j < list.size(); j++) {
						BookingDateRange old = list.get(j);
						if (dateRange.getStartDate() < old.getStartDate()
								&& dateRange.getEndDate() < old.getStartDate()) {
							exist = false;
						} else if (dateRange.getStartDate() > old.getEndDate()
								&& dateRange.getEndDate() > old.getEndDate()) {
							exist = false;
						} else {
							exist = true;
						}
					}
					if (!exist) {
						list.add(dateRange);
						map.put(i, list);
						return true;
					} else if (map.size() < Hotel.size) {
						List<BookingDateRange> newList = new LinkedList<BookingDateRange>();
						newList.add(dateRange);
						map.put(map.size(), newList);
						return true;
					}

				}
			}
		}
		return false;

	}

	@Override
	public boolean isValid(BookingDateRange dateRange) {
		if (dateRange.getStartDate() >= 0 && dateRange.getStartDate() <= Hotel.daysLimit && dateRange.getEndDate() >= 0
				&& dateRange.getEndDate() <= Hotel.daysLimit && dateRange.getStartDate()<=dateRange.getEndDate())
			return true;
		else
			return false;
	}
 
}
