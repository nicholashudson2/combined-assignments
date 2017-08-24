package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
//import com.cooksys.ftd.assignments.collections.model.WageSlave;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Set<Capitalist> capitalists = new HashSet<>();

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
		boolean added;

		if (has(capitalist) || capitalist == null) {
			added = false;
		} else {
			added = capitalists.add(capitalist);
		}
		
		
		
		if (has(capitalist.getParent()) && (capitalist instanceof FatCat)) {
			
			for (Capitalist c : capitalists) {
				
				if (c.getParent().equals(capitalist)) {
					capitalists.add(capitalist);
					added = true;
				}
			}
		}
		if (capitalist.hasParent()) {
			capitalists.add(capitalist);
			added = true;
			
			if (!capitalists.has(capitalist.getParent())) {
				capitalists.add(capitalist.getParent());
			}
		}
		return added;
	}

	/**
	 * @param capitalist
	 *            the element to search for
	 * @return true if the element has been added to the hierarchy, false
	 *         otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		if (capitalists.contains(capitalist)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		Set<Capitalist> elements = new HashSet<>();
		elements.addAll(capitalists);
		return elements;
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no
	 *         parents have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		Set<FatCat> parents = new HashSet<>();
		for (Capitalist c : capitalists) {
			parents.add(c.getParent());
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
		Set<Capitalist> children = new HashSet<>();
		for (Capitalist c : capitalists) {
			if (c.getParent() == fatCat) {
				children.add(c);
			}
		}
		return children;
	}

	/**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associated parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	Set<Capitalist> all = new HashSet<>();
    	Map<FatCat, Set<Capitalist>> heirarchy = new HashMap<>();
    	Set<Capitalist> children = new HashSet<>();
    	Set<FatCat> parents = new HashSet<>();
    	
    	all.addAll(capitalists);
    	
    	for(Capitalist cap : all) {
    		parents.add(cap.getParent());
    	}
    	
    	for(Capitalist p : parents) {
    		for(Capitalist c : children) {
    			if(c.getParent() == p) {
    				children.add(c);
    			}
    			heirarchy.put((FatCat)p, children);
    		}
    	}
    	return heirarchy;
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
		List<FatCat> parentChain = new ArrayList<>();
		Capitalist currentCapitalist = capitalist;
		do {
			parentChain.add(capitalist.getParent());
			currentCapitalist = capitalist.getParent();
		} while (currentCapitalist.hasParent());
		return parentChain;
	}

}
