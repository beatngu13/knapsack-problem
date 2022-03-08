package com.github.beatngu13.knapsackproblem.base;

import java.util.Objects;

/**
 * A knapsack, consisting of a set of {@link Item}s and a maximum capacity.
 *
 * @param items       The set of items in this knapsack.
 * @param maxCapacity The maximum capacity of the knapsack.
 */
public record Knapsack(Items items, int maxCapacity) {

	public Knapsack {
		Objects.requireNonNull(items, "Items must not be null.");
		if (maxCapacity < 0) {
			throw new IllegalArgumentException("Max capacity must not be negative.");
		}
	}

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

}
