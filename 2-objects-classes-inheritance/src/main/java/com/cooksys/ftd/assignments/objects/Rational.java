package com.cooksys.ftd.assignments.objects;

public class Rational implements IRational {
	private int numerator;
	private int denominator;

	/**
	 * Constructor for rational values of the type:
	 * <p>
	 * `numerator / denominator`
	 * <p>
	 * No simplification of the numerator/denominator pair should occur in this
	 * method.
	 *
	 * @param numerator
	 *            the numerator of the rational value
	 * @param denominator
	 *            the denominator of the rational value
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	public Rational(int numerator, int denominator) throws IllegalArgumentException {
		if (denominator != 0) {
			this.numerator = numerator;
			this.denominator = denominator;
		} else {
		throw new IllegalArgumentException("The denominator cannot equal 0.");
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
	 * @return the constructed rational value
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	@Override
	public Rational construct(int numerator, int denominator) throws IllegalArgumentException {
		Rational rational = new Rational(numerator, denominator);
		return rational;
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
		boolean isEqual = false; // Instantiates return value as false.

		if (obj instanceof Rational) { // Casts to Rational if types match.
			Rational object = (Rational) obj;
			
			if (this.getNumerator() == object.getNumerator() && this.getDenominator() == object.getDenominator()) { // Logic to determine if values are equal; Updates return value if true.
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
		int numerator = getNumerator();
		int denominator = getDenominator();
		String str = Math.abs(numerator) + "/" + Math.abs(denominator); // Declares return string using absolute values.
		if ((numerator < 0) != (denominator < 0)) // Logic to determine if rational value is negative, updating string to reflect if true.
			str = "-" + str;
		return str;
	}
}
