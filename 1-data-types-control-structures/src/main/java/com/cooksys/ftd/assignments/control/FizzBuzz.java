package com.cooksys.ftd.assignments.control;

import java.util.ArrayList;
import java.util.List;

/**
 * FizzBuzz is an old programming exercise. The goal is to iterate over a range
 * of numbers and print a message about each number's divisibility.
 * <p>
 * The message is generated the following way: *) if the number is divisible by
 * three, the message is `Fizz` *) if the number is divisible by five, the
 * message is `Buzz` *) if the number is divisible by both three and five, the
 * message is `FizzBuzz` *) otherwise, no message is produced
 * <p>
 * The exact message format for this assignment is specified in the `message(n)`
 * method.
 */
public class FizzBuzz {

	/**
	 * Checks whether a given int `a` is evenly divisible by a given int `b` or
	 * not. For example, `divides(4, 2)` returns `true` and `divides(4, 3)`
	 * returns `false`.
	 *
	 * @param a
	 *            the number to be divided
	 * @param b
	 *            the number to divide by
	 * @return `true` if a is evenly divisible by b, `false` otherwise
	 * @throws IllegalArgumentException
	 *             if b is zero
	 */
	public static boolean divides(int a, int b) throws IllegalArgumentException {
		boolean divisible = false; // Instantiates default return value of false.

		if (b != 0) { // Checks that b does not equal 0.
			if (a % b == 0) { // If a divides by b evenly, returns true.
				divisible = true;
			}
			return divisible;
		} else {
			throw new IllegalArgumentException("Divisor cannot equal 0");
		}
	}

	/**
	 * Generates a divisibility message for a given number. Returns null if the
	 * given number is not divisible by 3 or 5. Message formatting examples: 1
	 * -> null // not divisible by 3 or 5 3 -> "3: Fizz" // divisible by only 3
	 * 5 -> "5: Buzz" // divisible by only 5 15 -> "15: FizzBuzz" // divisible
	 * by both three and five
	 *
	 * @param n
	 *            the number to generate a message for
	 * @return a message according to the format above, or null if n is not
	 *         divisible by either 3 or 5
	 */
	public static String message(int n) {
		String message = null; // Instantiates default return value as null.
		
		if (divides(n, 5) || divides(n, 3)) { // Logic test to filter out integers not divisible by 3 or 5.
			if (divides(n, 15)) // Assigns return value for integers divisible by 3 and 5.
				message = n + ": FizzBuzz";
			if (divides(n, 5) && !divides(n, 3)) // Assigns return value for integers divisible by 5.
				message = n + ": Buzz";
			if (divides(n, 3) && !divides(n, 5))  // Assigns return value for integers divisible by 3.
				message = n + ": Fizz";
		}
		return message;
	}

	/**
	 * Generates an array of messages to print for a given range of numbers. If
	 * there is no message for an individual number (i.e., `message(n)` returns
	 * null), it should be excluded from the resulting array.
	 *
	 * @param start
	 *            the number to start with (inclusive)
	 * @param end
	 *            the number to end with (exclusive)
	 * @return an array of divisibility messages
	 * @throws IllegalArgumentException
	 *             if the given end is less than the given start
	 */
	public static String[] messages(int start, int end) throws IllegalArgumentException {
		List<String> messages = new ArrayList<>(); // Instantiates an empty ArrayList to contain return messages.
		
		if (end >= start) { // Logic test to filter out illegal arguments.
			for (int i = start; i < end; i++) { // Iterates through all integers.
				if (message(i) != null) { // Logic test; adds appropriate return messages to ArrayList.
					messages.add(message(i));
				}
			}
			return messages.toArray(new String[messages.size()]); // Instantiates and returns array from ArrayList above.
		} else throw new IllegalArgumentException("End parameter must be greater than or equal to Start parameter.");
	}

	/**
	 * For this main method, iterate over the numbers 1 through 115 and print
	 * the relevant messages to sysout
	 */
	public static void main(String[] args) {
		String[] messages = messages(1, 116); // Instantiates divisibility message array using Messages method.
		for (String s : messages) { // Iterates through array.
			System.out.println(s); // Outputs divisibility messages to system output stream.
		}
	}

}
