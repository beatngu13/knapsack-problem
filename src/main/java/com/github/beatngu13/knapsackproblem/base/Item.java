package com.github.beatngu13.knapsackproblem.base;

import lombok.Value;

/**
 * A knapsack item, consisting of a unique ID, a profit, and a weight.
 */
@Value
public class Item {

	/**
	 * A knapsack problem might have multiple items that share the same weight and
	 * profit, the ID ensures that items can still be uniquely identified.
	 */
	private final int id;
	/**
	 * The profit of the item.
	 */
	private final int profit;
	/**
	 * The weight of the item.
	 */
	private final int weight;

}
