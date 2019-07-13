package com.github.beatngu13.knapsackproblem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import lombok.experimental.UtilityClass;

// Knapsack problem based on P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html.
@UtilityClass
public class Problem {

	public static final int MAX_CAPACITY = 750;

	private static final List<Integer> profits = List.of( //
			135, 139, 149, 150, 156, //
			163, 173, 184, 192, 201, //
			210, 214, 221, 229, 240);

	private static final List<Integer> weights = List.of( //
			70, 73, 77, 80, 82, //
			87, 90, 94, 98, 106, //
			110, 113, 115, 118, 120);

	public static final List<Item> ITEMS = IntStream.range(0, profits.size()) //
			.mapToObj(i -> new Item(profits.get(i), weights.get(i))) //
			.collect(Collectors.toList());

	private static final String optimalSolution = "101010111000011";

	public static final Knapsack OPTIMAL_KNAPSACK = getOptimalKnapsack();

	private static Knapsack getOptimalKnapsack() {
		return IntStream.range(0, optimalSolution.length()) //
				.mapToObj(i -> optimalSolution.charAt(i) == '1' ? ITEMS.get(i) : null) //
				.filter(Objects::nonNull) //
				.collect(Collectors.collectingAndThen(Collectors.toList(), Knapsack::new));
	}

}
