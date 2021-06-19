package com.github.beatngu13.knapsackproblem.util;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ProblemUtil {

	private ProblemUtil() {

	}

	/**
	 * @param profits The list of profits.
	 * @param weights The list of weights.
	 * @return The list of items by combining each profit and weight pair.
	 */
	public static List<Item> getItems(final List<Integer> profits, final List<Integer> weights) {
		return IntStream.range(0, profits.size())
				.mapToObj(i -> new Item(profits.get(i), weights.get(i)))
				.toList();
	}

	/**
	 * @param optimalSolution The bit string of the optimal solution.
	 * @param items           The list of items.
	 * @param maxCapacity     The maximum capacity of the knapsack.
	 * @return The optimal knapsack by selecting the corresponding items based on
	 * the given bit string.
	 */
	public static Knapsack getOptimalKnapsack(final String optimalSolution, final List<Item> items,
											  final int maxCapacity) {
		return IntStream.range(0, optimalSolution.length())
				.mapToObj(i -> getItem(i, optimalSolution, items))
				.flatMap(Optional::stream)
				.collect(Collectors.collectingAndThen(Items.collector(),
						optimalItems -> KnapsackFactory.create(optimalItems, maxCapacity)));
	}

	private static Optional<Item> getItem(final int i, final String optimalSolution, final List<Item> items) {
		return optimalSolution.charAt(i) == '1' ? Optional.of(items.get(i)) : Optional.empty();
	}

}
