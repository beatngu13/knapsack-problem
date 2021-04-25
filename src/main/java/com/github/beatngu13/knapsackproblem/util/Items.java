package com.github.beatngu13.knapsackproblem.util;

import com.github.beatngu13.knapsackproblem.base.Item;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * The set of items must have predictable iteration order
 * (e.g. because of {@link com.github.beatngu13.knapsackproblem.mo.ga.KnapsackChromosome#get(int)}). This class provides
 * utility methods to retrieve sets that fulfill this requirement.
 */
public class Items {

	private Items() {

	}

	public static Set<Item> set() {
		return new LinkedHashSet<>();
	}

	public static Set<Item> set(final Collection<Item> items) {
		return new LinkedHashSet<>(items);
	}

	public static Collector<Item, ?, Set<Item>> collector() {
		return Collectors.toCollection(LinkedHashSet::new);
	}

}
