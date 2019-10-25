package com.github.beatngu13.knapsackproblem.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import lombok.experimental.UtilityClass;

// Knapsack problem based on P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html.
@UtilityClass
public class ProblemUtil {

	public static List<Item> getItems(final List<Integer> profits, final List<Integer> weights) {
		return IntStream.range(0, profits.size()) //
				.mapToObj(i -> new Item(i, profits.get(i), weights.get(i))) //
				.collect(Collectors.toList());
	}

	public static Knapsack getOptimalKnapsack(final String optimalSolution, final List<Item> items,
			final int maxCapacity) {
		return IntStream.range(0, optimalSolution.length()) //
				.mapToObj(i -> optimalSolution.charAt(i) == '1' ? items.get(i) : null) //
				.filter(Objects::nonNull) //
				.collect(Collectors.collectingAndThen(Collectors.toSet(), i -> new Knapsack(i, maxCapacity)));
	}

}
