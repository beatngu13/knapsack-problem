package com.github.beatngu13.knapsackproblem.util;

import com.github.beatngu13.knapsackproblem.base.Item;

import java.io.Serial;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * A set of knapsack items with predictable iteration order.
 *
 * @implNote This is needed, e.g., because of {@link com.github.beatngu13.knapsackproblem.mo.ga.KnapsackChromosome#get(int)}.
 */
public class Items extends LinkedHashSet<Item> {

	@Serial
	private static final long serialVersionUID = 363722257281710729L;

	public Items() {
		super();
	}

	public Items(final Collection<Item> items) {
		super(items);
	}

}
