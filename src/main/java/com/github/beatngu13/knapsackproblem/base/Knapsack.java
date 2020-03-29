package com.github.beatngu13.knapsackproblem.base;

import java.util.Set;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * A knapsack, consisting of a set of {@link Item}s and a maximum capacity.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Knapsack {

	/**
	 * The set of items in this knapsack.
	 */
	private final Set<Item> items;
	/**
	 * The maximum capacity of the knapsack.
	 */
	private final int maxCapacity;

	/**
	 * @return The summarized profit of all items in this knapsack.
	 */
	public int getProfit() {
		return items.stream() //
				.mapToInt(Item::getProfit) //
				.sum();
	}

	/**
	 * @return The summarized weight of all items in this knapsack.
	 */
	public int getWeight() {
		return items.stream() //
				.mapToInt(Item::getWeight) //
				.sum();
	}

	/**
	 * @return <code>true</code> if the summarized weight of all items does not
	 *         exceed the maximum capacity.
	 */
	public boolean isWithinMaxCapacity() {
		return getWeight() <= maxCapacity;
	}

	@Override
	public String toString() {
		return "Knapsack(profit=" + getProfit() + ", weight=" + getWeight() + ", max capacity=" + getMaxCapacity()
				+ ")";
	}

}
