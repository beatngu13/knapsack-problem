package com.github.beatngu13.knapsackproblem.so;

import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import io.jenetics.util.RandomRegistry;

import java.util.stream.Collectors;

public final class KnapsackFactory {

	private KnapsackFactory() {

	}

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 * {@link Problem#MAX_CAPACITY}.
	 */
	public static Knapsack create(final Items items) {
		return new Knapsack(items, Problem.MAX_CAPACITY);
	}

	/**
	 * @return A new knapsack with random items from
	 * {@link Problem#ITEMS} and a maximum capacity of
	 * {@link Problem#MAX_CAPACITY}.
	 */
	public static Knapsack createRandom() {
		final var random = RandomRegistry.random();
		final var items = Problem.ITEMS.stream()
				.filter(_ -> random.nextBoolean())
				.collect(Collectors.toCollection(Items::new));
		final var knapsack = create(items);
		return knapsack.isWithinMaxCapacity() ? knapsack : createRandom();
	}

}
