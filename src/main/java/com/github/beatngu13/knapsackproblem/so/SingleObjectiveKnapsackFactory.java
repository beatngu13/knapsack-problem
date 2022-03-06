package com.github.beatngu13.knapsackproblem.so;

import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import io.jenetics.util.RandomRegistry;

import java.util.stream.Collectors;

public final class SingleObjectiveKnapsackFactory {

	private SingleObjectiveKnapsackFactory() {

	}

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 * {@link SingleObjectiveProblem#MAX_CAPACITY}.
	 */
	public static Knapsack create(final Items items) {
		return new Knapsack(items, SingleObjectiveProblem.MAX_CAPACITY);
	}

	/**
	 * @return A new knapsack with random items from
	 * {@link SingleObjectiveProblem#ITEMS} and a maximum capacity of
	 * {@link SingleObjectiveProblem#MAX_CAPACITY}.
	 */
	public static Knapsack createRandom() {
		final var random = RandomRegistry.random();
		final var items = SingleObjectiveProblem.ITEMS.stream()
				.filter(item -> random.nextBoolean())
				.collect(Collectors.toCollection(Items::new));
		final var knapsack = create(items);
		return knapsack.isWithinMaxCapacity() ? knapsack : createRandom();
	}

}
