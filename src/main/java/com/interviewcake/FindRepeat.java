package com.interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

/*
Find a duplicate, Space Edition™.

We have an array of integers, where:

The integers are in the range 1..n1..n
The array has a length of n+1n+1
It follows that our array has at least one integer which appears at least twice. But it may have several duplicates, and each duplicate may appear more than twice.

Write a method which finds an integer that appears more than once in our array. (If there are multiple duplicates, you only need to find one of them.)

We're going to run this method on our new, super-hip MacBook Pro With Retina Display™. Thing is, the damn thing came with the RAM soldered right to the motherboard, so we can't upgrade our RAM. So we need to optimize for space!
*/
public class FindRepeat {

	public static int findRepeat(int[] numbers) {

		int floor = 1;
		int ceiling = numbers.length - 1;

		while (floor < ceiling) {
			int midpoint = floor + ((ceiling - floor) / 2);
			int lowerRangeFloor = floor;
			int lowerRangeCeiling = midpoint;
			int upperRangeFloor = midpoint + 1;
			int upperRangeCeiling = ceiling;

			int itemsInLowerRange = 0;
			for (int item : numbers) {
				if (item >= lowerRangeFloor && item <= lowerRangeCeiling) {
					itemsInLowerRange++;
				}
			}

			int distinctPossibleIntegersInLowerRange = lowerRangeCeiling - lowerRangeFloor + 1;
			if (itemsInLowerRange > distinctPossibleIntegersInLowerRange) {
				floor = lowerRangeFloor;
				ceiling = lowerRangeCeiling;
			} else {
				floor = upperRangeFloor;
				ceiling = upperRangeCeiling;
			}
		}

		return floor;
	}

	// tests

	@Test
	public void justTheRepeatedNumberTest() {
		final int[] numbers = { 1, 1 };
		final int expected = 1;
		final int actual = findRepeat(numbers);
		assertEquals(expected, actual);
	}

	@Test
	public void shortArrayTest() {
		final int[] numbers = { 1, 2, 3, 2 };
		final int expected = 2;
		final int actual = findRepeat(numbers);
		assertEquals(expected, actual);
	}

	@Test
	public void mediumArrayTest() {
		final int[] numbers = { 1, 2, 5, 5, 5, 5 };
		final int expected = 5;
		final int actual = findRepeat(numbers);
		assertEquals(expected, actual);
	}

	@Test
	public void longArrayTest() {
		final int[] numbers = { 4, 1, 4, 8, 3, 2, 7, 6, 5 };
		final int expected = 4;
		final int actual = findRepeat(numbers);
		assertEquals(expected, actual);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(FindRepeat.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests passed.");
		}
	}
}