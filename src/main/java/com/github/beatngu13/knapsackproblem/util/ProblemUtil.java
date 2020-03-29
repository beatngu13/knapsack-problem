package com.github.beatngu13.knapsackproblem.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProblemUtil {

	/**
	 * @param profits The list of profits.
	 * @param weights The list of weights.
	 * @return The list of items by combining each profit and weight pair.
	 */
	public static List<Item> getItems(final List<Integer> profits, final List<Integer> weights) {
		return IntStream.range(0, profits.size()) //
				.mapToObj(i -> new Item(i, profits.get(i), weights.get(i))) //
				.collect(Collectors.toList());
	}

	/**
	 * @param optimalSolution The bit string of the optimal solution.
	 * @param items           The list of items.
	 * @param maxCapacity     The maximum capacity of the knapsack.
	 * @return The optimal knapsack by selecting the corresponding items based on
	 *         the given bit string.
	 */
	public static Knapsack getOptimalKnapsack(final String optimalSolution, final List<Item> items,
			final int maxCapacity) {
		return IntStream.range(0, optimalSolution.length()) //
				.mapToObj(i -> optimalSolution.charAt(i) == '1' ? items.get(i) : null) //
				.filter(Objects::nonNull) //
				.collect(Collectors.collectingAndThen(Collectors.toSet(),
						optimalItems -> KnapsackFactory.create(optimalItems, maxCapacity)));
	}

}
