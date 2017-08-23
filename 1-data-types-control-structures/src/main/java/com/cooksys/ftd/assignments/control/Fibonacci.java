package com.cooksys.ftd.assignments.control;

/**
 * The Fibonacci sequence is simply and recursively defined: the first two
 * elements are `1`, and every other element is equal to the sum of its two
 * preceding elements. For example:
 * <p>
 * [1, 1] => [1, 1, 1 + 1] => [1, 1, 2] => [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] => ...etc
 */
public class Fibonacci {

	/**
	 * Calculates the value in the Fibonacci sequence at a given index. For
	 * example, `atIndex(0)` and `atIndex(1)` should return `1`, because the
	 * first two elements of the sequence are both `1`.
	 *
	 * @param i
	 *            the index of the element to calculate
	 * @return the calculated element
	 * @throws IllegalArgumentException
	 *             if the given index is less than zero
	 */
	public static int atIndex(int i) throws IllegalArgumentException {
		int sum = 0; // Variable to hold value of next index during loop below.
		int previousIndex = 1; // Variable to hold previous index value for calculation.
		int beforePreviousIndex = 0; // Variable to hold 2nd previous index value for calculation.

		if (i > 0) { // Logic test to filter out values less than 0.
			if (i != 0) { // Logic test to filter out values equal to 0.
				for (int index = 1; index <= i; index++) { // Iterates through all integers from 1 through i.
					sum = previousIndex + beforePreviousIndex; // Updates sum to contain current index value.
					beforePreviousIndex = previousIndex; // Updates second previous index value.
					previousIndex = sum; // Updates previous index value.
				}
				return sum; // Returns value at index i if i is not negative or equal to 0.
			} else {
				return 1; // Returns 1 if i is equal to 0.
			}
		} else {
			throw new IllegalArgumentException("Index cannot be less than 0."); // Returns exception if i is negative.
		}
	}

	/**
	 * Calculates a slice of the fibonacci sequence, starting from a given start
	 * index (inclusive) and ending at a given end index (exclusive).
	 *
	 * @param start
	 *            the starting index of the slice (inclusive)
	 * @param end
	 *            the ending index of the slice(exclusive)
	 * @return the calculated slice as an array of int elements
	 * @throws IllegalArgumentException
	 *             if either the given start or end is negative, or if the given
	 *             end is less than the given start
	 */
	public static int[] slice(int start, int end) throws IllegalArgumentException {
		int[] slice; // Declares array to be used in logic test.
		int index; // Declares index variable used in logic test.

		if (end > start || end < 0) { // Logic test to filter non negative integers, and instances where start value is greater than end value.
			slice = new int[end - start]; // Instantiates array declared above, with size equal to end - start.
			index = 0; // Instantiates index variable used in for loop.

			for (int i = start; i < end; i++) { // Iterates over integers between start and end.
				slice[index] = atIndex(i); // Obtains value corresponding to index.
				index++; // Increments index variable.
			}
			return slice; // Returns array of values corresponding to given indices.
		}
		throw new IllegalArgumentException(
				"Start and End parameters must be non-negative integers; End cannot be less than Start.");
	}

	/**
	 * Calculates the beginning of the fibonacci sequence, up to a given count.
	 *
	 * @param count
	 *            the number of elements to calculate
	 * @return the beginning of the fibonacci sequence, up to the given count,
	 *         as an array of int elements
	 * @throws IllegalArgumentException
	 *             if the given count is negative
	 */
	public static int[] fibonacci(int count) throws IllegalArgumentException {
		return slice(0, count); // Simply returns array containing values corresponding to indices 0 through 115.
	}
}
