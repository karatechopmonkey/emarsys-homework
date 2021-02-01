package com.emarsys.duedatecalculator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DueDateCalculator {
	
	private static final LocalTime START_BUSINESS_HOUR = LocalTime.of(9, 0, 0);
	private static final LocalTime FINISH_BUSINESS_HOUR = LocalTime.of(17, 0, 0);
	
	

	public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnAroundTimeInHours) {
		validate(submitDateTime, turnAroundTimeInHours);
		LocalDateTime dueDate = submitDateTime;
			for (int hourCountdown = turnAroundTimeInHours; hourCountdown > 0; hourCountdown--) {
				dueDate = dueDate.plusHours(1);
				if (!dueDate.toLocalTime().isBefore(FINISH_BUSINESS_HOUR)) {
					dueDate = dueDate.plusDays(dueDate.getDayOfWeek() == DayOfWeek.FRIDAY ? 3 : 1 );
					dueDate = dueDate.withHour(START_BUSINESS_HOUR.getHour());
				}
			}
		return dueDate;
	}

	private void validate(LocalDateTime submitDateTime, int turnAroundTimeInHours) {
		if (!isWeekday(submitDateTime) || !isBusinessHours(submitDateTime)) {
			throw new IllegalArgumentException("Submit date out of bounds.");
		}
		if (turnAroundTimeInHours <= 0) {
			throw new IllegalArgumentException("Turnaround time has to be positive.");
		}
	}

	private boolean isBusinessHours(LocalDateTime submitDateTime) {
		LocalTime localTime = submitDateTime.toLocalTime();
		if (!localTime.isBefore(START_BUSINESS_HOUR) && localTime.isBefore(FINISH_BUSINESS_HOUR)) {
			return true;
		}
		return false;
	}

	private boolean isWeekday(LocalDateTime submitDateTime) {
		return switch (submitDateTime.getDayOfWeek()) {
		case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> true;
		case SATURDAY, SUNDAY -> false;
		};
	}
}
