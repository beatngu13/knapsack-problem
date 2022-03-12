package com.github.beatngu13.knapsackproblem.so;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import java.util.List;

/**
 * Single-objective knapsack problem based on P07 from <a href=
 * "https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html">https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html</a>.
 */
public final class Problem {

	private Problem() {

	}

	public static final int MAX_CAPACITY = 750;

	public static final List<Item> ITEMS = List.of(
			new Item(135, 70),
			new Item(139, 73),
			new Item(149, 77),
			new Item(150, 80),
			new Item(156, 82),
			new Item(163, 87),
			new Item(173, 90),
			new Item(184, 94),
			new Item(192, 98),
			new Item(201, 106),
			new Item(210, 110),
			new Item(214, 113),
			new Item(221, 115),
			new Item(229, 118),
			new Item(240, 120)
	);

	public static final Knapsack OPTIMAL_KNAPSACK = new Knapsack(
			// Bitstring: 101010111000011
			new Items(
					ITEMS.get(0),
					ITEMS.get(2),
					ITEMS.get(4),
					ITEMS.get(6),
					ITEMS.get(7),
					ITEMS.get(8),
					ITEMS.get(13),
					ITEMS.get(14)
			),
			MAX_CAPACITY);

}
