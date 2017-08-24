package com.cooksys.ftd.assignments.collections.model;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FatCat implements Capitalist {

	private String name;
	private int salary;
	private FatCat owner;

	/**
	 * @param name
	 *            a string value representing the name of the element to be
	 *            constructed
	 * @param salary
	 *            an int value representing the salary of the constructed
	 *            element
	 */
	public FatCat(String name, int salary) {
		this(name, salary, null); // Constructs an element using the next
									// constructor, passing a null value for
									// salary.
	}

	/**
	 * @param name
	 *            a string value representing the name of the element to be
	 *            constructed
	 * @param salary
	 *            an int value representing the salary of the constructed
	 *            element
	 * @param owner
	 *            the FatCat element that is the constructed elements direct
	 *            parent
	 */
	public FatCat(String name, int salary, FatCat owner) {
		this.name = name; // Sets the constructed elements name to the given
							// name
		this.salary = salary; // Sets the constructed elements salary to the
								// given salary
		this.owner = owner; // Sets the constructed elements parent to the given
							// owner
	}

	/**
	 * @return the name of the capitalist
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @return the salary of the capitalist, in dollars
	 */
	@Override
	public int getSalary() {
		return this.salary;
	}

	/**
	 * @return true if this element has a parent, or false otherwise
	 */
	@Override
	public boolean hasParent() {
        boolean hasParent = (this.owner != null); // Returns true if this element has a parent element, otherwise returns false.
        return hasParent;
	}

	/**
	 * @return the parent of this element, or null if this represents the top of
	 *         a hierarchy
	 */
	@Override
	public FatCat getParent() {
        if(this.hasParent()) { // Logical test determining if this element has a parent element
        	return this.owner; // Returns parent element if logical test passes
        }
        return null; // Returns null otherwise
	}

	/**
	 * @return the hashCode for this element
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + salary;
		return result;
	}

	/**
	 * @return true if this element is equal to the given element, or false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FatCat other = (FatCat) obj;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}

	/**
	 * @return a clone of the capitalist element
	 */
	@Override
	public Capitalist clone() {
		return new FatCat(name, salary, owner);
	}

}
