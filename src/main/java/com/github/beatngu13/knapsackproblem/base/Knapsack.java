package com.github.beatngu13.knapsackproblem.base;

import java.util.Set;

/**
 * A knapsack, consisting of a set of {@link Item}s and a maximum capacity.
 *
 * @param items       The set of items in this knapsack.
 * @param maxCapacity The maximum capacity of the knapsack.
 */
public record Knapsack(Set<Item> items, int maxCapacity) {

	/**
	 * @return The summarized profit of all items in this knapsack.
	 */
	public int profit() {
		return items.stream()
				.mapToInt(Item::profit)
				.sum();
	}

	/**
	 * @return The summarized weight of all items in this knapsack.
	 */
	public int weight() {
		return items.stream()
				.mapToInt(Item::weight)
				.sum();
	}

	/**
	 * @return <code>true</code> if the summarized weight of all items does not
	 * exceed the maximum capacity.
	 */
	public boolean isWithinMaxCapacity() {
		return weight() <= maxCapacity;
	}

	@Override
	public String toString() {
		return "Knapsack[profit=" + profit() + ", weight=" + weight() + ", maxCapacity=" + maxCapacity() + "]";
	}

}
