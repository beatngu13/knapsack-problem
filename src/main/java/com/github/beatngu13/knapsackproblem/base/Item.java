package com.github.beatngu13.knapsackproblem.base;

import lombok.Value;

@Value
public class Item {

	/**
	 * A knapsack problem might have multiple items that share the same weight and
	 * profit, the ID ensures that items can still be uniquely identified.
	 */
	private final int id;
	private final int profit;
	private final int weight;

}
