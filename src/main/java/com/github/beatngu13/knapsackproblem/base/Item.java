package com.github.beatngu13.knapsackproblem.base;

/**
 * A knapsack item, consisting of a profit and a weight.
 *
 * @implNote Items are unique, therefore, {@link #equals(Object)} and {@link #hashCode()} are based on identity.
 */
public class Item {

	private final int profit;
	private final int weight;

	public Item(final int profit, final int weight) {
		this.profit = profit;
		this.weight = weight;
	}

	public int profit() {
		return profit;
	}

	public int weight() {
		return weight;
	}

}
