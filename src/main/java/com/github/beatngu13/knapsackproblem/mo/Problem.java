package com.github.beatngu13.knapsackproblem.mo;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import java.util.List;

/**
 * Multi-objective knapsack problem based on P06 from <a href=
 * "https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html">https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html</a>.
 */
public final class Problem {

	private Problem() {

	}

	public static final int MAX_CAPACITY_0 = 103;
	public static final int MAX_CAPACITY_1 = 156;

	public static final List<Item> ITEMS = List.of(
			new Item(78, 18),
			new Item(35, 9),
			new Item(89, 23),
			new Item(36, 20),
			new Item(94, 59),
			new Item(75, 61),
			new Item(74, 70),
			new Item(79, 75),
			new Item(80, 76),
			new Item(16, 30)
	);

	public static final Knapsack OPTIMAL_KNAPSACK_0 = new Knapsack(
			// Bitstring: 1010010000
			new Items(
					ITEMS.get(0),
					ITEMS.get(2),
					ITEMS.get(5)
			),
			MAX_CAPACITY_0);
	public static final Knapsack OPTIMAL_KNAPSACK_1 = new Knapsack(
			// Bitstring: 0001100010
			new Items(
					ITEMS.get(3),
					ITEMS.get(4),
					ITEMS.get(8)
			),
			MAX_CAPACITY_1);

	public static final int NUMBER_OF_KNAPSACKS = 2;

}
