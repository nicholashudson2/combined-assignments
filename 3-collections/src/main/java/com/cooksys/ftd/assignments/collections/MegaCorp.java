package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Set<Capitalist> capitalistSet = new HashSet<>();

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it
	 * and return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add
	 * it and return false
	 *
	 * @param capitalist
	 *            the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {

		if (capitalist == null || (!capitalist.hasParent() && capitalist instanceof WageSlave)) { // Logical test to return false for elements not added to heirarchy.
			return false;
		} else {
			add(capitalist.getParent()); // Adds all elements not filtered above to heirarchy.
		}
		return capitalistSet.add(capitalist);
	}

	/**
	 * @param capitalist
	 *            the element to search for
	 * @return true if the element has been added to the hierarchy, false
	 *         otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		return (capitalistSet.contains(capitalist)); // Returns true/false indicating presence of element in hierarchy.
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		return new HashSet<>(capitalistSet); // Returns copy of the hierarchy.
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no
	 *         parents have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		Set<FatCat> parents = new HashSet<>(); // Instantiates empty set
		for (Capitalist c : getElements()) { // Iterates through all elements in hierarchy.
			if (c instanceof FatCat) { // Logical test filters out all parent elements.
				parents.add((FatCat) c); // Adds parent elements to set, casting as FatCat.
			}
		}
		return parents;
	}

	/**
	 * @param fatCat
	 *            the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a
	 *         direct parent, or an empty set if the parent is not present in
	 *         the hierarchy or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {
		Set<Capitalist> children = new HashSet<>(); // Instantiates empty set.

		if (!has(fatCat)) // Logical test verifying hierarchy contains given element.
			return children; // Returns empty set if element is not in hierarchy.
		for (Capitalist c : capitalistSet) { // Iterates through all elements in hierarchy.
			if (fatCat == c.getParent()) // Logical test comparing parent of current loop element to given element.
				children.add(c); // Adds child element to return set.
		}
		return children;
	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of
	 *         the associated parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {
		Map<FatCat, Set<Capitalist>> heirarchyMap = new HashMap<>(); // Instantiates empty HashMap.

			for (Capitalist c : getParents()) { // Iterates through all parent elements.
				heirarchyMap.put(((FatCat) c.clone()), getChildren((FatCat)c)); // Adds current loop element as parent key, set of children elements as value in HashMap.
			}
		return heirarchyMap;
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the
	 *         given element has no parent or if its parent is not in the
	 *         hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {
		List<FatCat> parentChain = new ArrayList<>(); // Instantiates empty ArrayList to contain return values.

		if (capitalist != null) { // Logical test to verify given element is not null.
			while (capitalist.hasParent() && has(capitalist.getParent())) { // Logical test instantiating loop only while current element has a parent that is contained in the hierarchy.
					capitalist = capitalist.getParent(); // Updates capitalist element to the current parent element
					parentChain.add((FatCat)capitalist); // Adds current parent element to the ArrayList.
			}
		}
		return parentChain; // Returns ArrayList containing parent elements, or empty list if first logical test fails.
	}

}
