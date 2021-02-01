package com.emarsys.duedatecalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DueDateCalculatorTest {

	@Test
	void When_Monday14H22M_And_TurnAround96H_Expect_2WeeksLaterWednesday14H22M() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 1, 14, 22);
		int turnAroundTimeInHours = 96;
		// when
		LocalDateTime dueDate = calc.calculateDueDate(submitDateTime, turnAroundTimeInHours);
		// then
		LocalDateTime expectedDueDate = LocalDateTime.of(2021, 2, 17, 14, 22);
		assertEquals(expectedDueDate, dueDate);
	}
	
	@Test
	void When_Monday14H22M_And_TurnAround16H_Expect_Wednesday14H22M() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 1, 14, 22);
		int turnAroundTimeInHours = 16;
		// when
		LocalDateTime dueDate = calc.calculateDueDate(submitDateTime, turnAroundTimeInHours);
		// then
		LocalDateTime expectedDueDate = LocalDateTime.of(2021, 2, 3, 14, 22);
		assertEquals(expectedDueDate, dueDate);
	}

	@Test
	void When_Wednesday9H15M_And_TurnAround16H_Expect_Friday9H15M() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 3, 9, 15);
		int turnAroundTimeInHours = 16;
		// when
		LocalDateTime dueDate = calc.calculateDueDate(submitDateTime, turnAroundTimeInHours);
		// then
		LocalDateTime expectedDueDate = LocalDateTime.of(2021, 2, 5, 9, 15);
		assertEquals(expectedDueDate, dueDate);
	}
	
	@Test
	void When_Wednesday9H00M_And_TurnAround8H_Expect_Thursday9H00M() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 3, 9, 0);
		int turnAroundTimeInHours = 8;
		// when
		LocalDateTime dueDate = calc.calculateDueDate(submitDateTime, turnAroundTimeInHours);
		// then
		LocalDateTime expectedDueDate = LocalDateTime.of(2021, 2, 4, 9, 0);
		assertEquals(expectedDueDate, dueDate);
	}
	
	@Test
	void When_Wednesday16H59M_And_TurnAround8H_Expect_Thursday16H59M() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 3, 16, 59);
		int turnAroundTimeInHours = 8;
		// when
		LocalDateTime dueDate = calc.calculateDueDate(submitDateTime, turnAroundTimeInHours);
		// then
		LocalDateTime expectedDueDate = LocalDateTime.of(2021, 2, 4, 16, 59);
		assertEquals(expectedDueDate, dueDate);
	}
	
	@Test
	void When_Friday11H30M_And_TurnAround8H_Expect_Monday11H30M() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 5, 11, 30);
		int turnAroundTimeInHours = 8;
		// when
		LocalDateTime dueDate = calc.calculateDueDate(submitDateTime, turnAroundTimeInHours);
		// then
		LocalDateTime expectedDueDate = LocalDateTime.of(2021, 2, 8, 11, 30);
		assertEquals(expectedDueDate, dueDate);
	}
	
	@Test
	void When_Saturday11H30M_And_TurnAround8H_Expect_IllegalArgumentException() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 6, 11, 30);
		int turnAroundTimeInHours = 8;
		// when - then
		assertThrows(IllegalArgumentException.class, () -> calc.calculateDueDate(submitDateTime, turnAroundTimeInHours));
	}
	
	@Test
	void When_Friday17H30M_And_TurnAround8H_Expect_IllegalArgumentException() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 5, 17, 30);
		int turnAroundTimeInHours = 8;
		// when - then
		assertThrows(IllegalArgumentException.class, () -> calc.calculateDueDate(submitDateTime, turnAroundTimeInHours));
	}
	
	@Test
	void When_Friday7H30M_And_TurnAround8H_Expect_IllegalArgumentException() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 5, 7, 30);
		int turnAroundTimeInHours = 8;
		// when - then
		assertThrows(IllegalArgumentException.class, () -> calc.calculateDueDate(submitDateTime, turnAroundTimeInHours));
	}
	
	@Test
	void When_Friday9H30M_And_TurnAround0H_Expect_IllegalArgumentException() {
		// given
		DueDateCalculator calc = new DueDateCalculator();
		LocalDateTime submitDateTime = LocalDateTime.of(2021, 2, 5, 9, 30);
		int turnAroundTimeInHours = 0;
		// when - then
		assertThrows(IllegalArgumentException.class, () -> calc.calculateDueDate(submitDateTime, turnAroundTimeInHours));
	}
}
