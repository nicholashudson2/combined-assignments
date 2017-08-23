package com.cooksys.ftd.assignments.objects;

public class SimplifiedRational implements IRational {
	private int numerator;
	private int denominator;

	/**
	 * Determines the greatest common denominator for the given values
	 *
	 * @param a
	 *            the first value to consider
	 * @param b
	 *            the second value to consider
	 * @return the greatest common denominator, or shared factor, of `a` and `b`
	 * @throws IllegalArgumentException
	 *             if a <= 0 or b < 0
	 */
	public static int gcd(int a, int b) throws IllegalArgumentException {
		if (a <= 0 || b < 0) {
			throw new IllegalArgumentException(
					"Parameter a must be a positive, non-zero integer; Parameter b must be a positive integer.");
		} else { 
			if (a == 0) // Uses Euclidean algorithm to efficiently find greatest common divisor/denominator.
				return b;
			while (b != 0) {
				if (a > b)
					a = a - b;
				else
					b = b - a;
			}
		}
		return a;
	}

	/**
	 * Simplifies the numerator and denominator of a rational value.
	 * <p>
	 * For example: `simplify(10, 100) = [1, 10]` or: `simplify(0, 10) = [0, 1]`
	 *
	 * @param numerator
	 *            the numerator of the rational value to simplify
	 * @param denominator
	 *            the denominator of the rational value to simplify
	 * @return a two element array representation of the simplified numerator
	 *         and denominator
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
		int[] simplified = new int[] {numerator, denominator}; // Instantiates array with initial values; returns this if numerator = 0.
		int index = 0;

		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot equal 0.");
		}
		
		for(int i : simplified) {
			simplified[index] = i / gcd(Math.abs(numerator), Math.abs(denominator)); // Iterates over array, dividing both values contained within by the greatest common denominator, and updating the array contents.
			index++;
		}
		return simplified;
	}

	/**
	 * Constructor for rational values of the type:
	 * <p>
	 * `numerator / denominator`
	 * <p>
	 * Simplification of numerator/denominator pair should occur in this method.
	 * If the numerator is zero, no further simplification can be performed
	 *
	 * @param numerator
	 *            the numerator of the rational value
	 * @param denominator
	 *            the denominator of the rational value
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
		int[] simplifiedArray;
		this.numerator = numerator; // Sets numerator & denominator value; Not changed if numerator equals 0.
		this.denominator = denominator;
		
		if (denominator != 0) {
			if (numerator != 0) { // Simplifies both values if numerator does not equal zero.
				simplifiedArray = SimplifiedRational.simplify(numerator, denominator);
				this.numerator = simplifiedArray[0];
				this.denominator = simplifiedArray[1];
			}
		} else {
		throw new IllegalArgumentException("Denominator cannot equal 0.");
		}
	}

	/**
	 * @return the numerator of this rational number
	 */
	@Override
	public int getNumerator() {
		return this.numerator;
	}

	/**
	 * @return the denominator of this rational number
	 */
	@Override
	public int getDenominator() {
		return this.denominator;
	}

	/**
	 * Specializable constructor to take advantage of shared code between
	 * Rational and SimplifiedRational
	 * <p>
	 * Essentially, this method allows us to implement most of IRational methods
	 * directly in the interface while preserving the underlying type
	 *
	 * @param numerator
	 *            the numerator of the rational value to construct
	 * @param denominator
	 *            the denominator of the rational value to construct
	 * @return the constructed rational value (specifically, a
	 *         SimplifiedRational value)
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	@Override
	public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
		return new SimplifiedRational(numerator, denominator);
	}

	/**
	 * @param obj
	 *            the object to check this against for equality
	 * @return true if the given obj is a rational value and its numerator and
	 *         denominator are equal to this rational value's numerator and
	 *         denominator, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false; // Instantiates return statement with default value as false.

		if (obj instanceof SimplifiedRational) { // Casts object to type SimplifiedRational if type matches.
			SimplifiedRational object = (SimplifiedRational) obj;
			if (this.getNumerator() == object.getNumerator() && this.getDenominator() == object.getDenominator()) { // Logic to update return value if true.
				isEqual = true;
			}
		}
		return isEqual; 
	}

	/**
	 * If this is positive, the string should be of the form
	 * `numerator/denominator`
	 * <p>
	 * If this is negative, the string should be of the form
	 * `-numerator/denominator`
	 *
	 * @return a string representation of this rational value
	 */
	@Override
	public String toString() {
		String str = Math.abs(numerator) + "/" + Math.abs(denominator); // Declares return string using absolute values.
		if ((numerator < 0) != (denominator < 0)) // Logic to determine if simplified rational value is negative, updating string to reflect if true.
			str = "-" + str;
		return str;
	}
}
