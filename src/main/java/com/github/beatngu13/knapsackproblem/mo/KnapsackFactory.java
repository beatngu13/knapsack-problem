package com.github.beatngu13.knapsackproblem.mo;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import io.jenetics.util.RandomRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class KnapsackFactory {

	private KnapsackFactory() {

	}

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 * {@link Problem#MAX_CAPACITY_0}.
	 */
	public static Knapsack createFirst(final Items items) {
		return new Knapsack(items, Problem.MAX_CAPACITY_0);
	}

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 * {@link Problem#MAX_CAPACITY_1}.
	 */
	public static Knapsack createSecond(final Items items) {
		return new Knapsack(items, Problem.MAX_CAPACITY_1);
	}

	/**
	 * @return A new knapsack with random items from
	 * {@link Problem#ITEMS} and the given maximum capacity.
	 */
	public static Knapsack createRandom(final int maxCapacity) {
		final var random = RandomRegistry.random();
		final var items = Problem.ITEMS.stream()
				.filter(_ -> random.nextBoolean())
				.collect(Collectors.toCollection(Items::new));
		final var knapsack = new Knapsack(items, maxCapacity);
		return knapsack.isWithinMaxCapacity() ? knapsack : createRandom(maxCapacity);
	}

	/**
	 * @return A list of new knapsacks with random, mutually exclusive items from
	 * {@link Problem#ITEMS} and a maximum capacity of
	 * {@link Problem#MAX_CAPACITY_0} for the first and
	 * {@link Problem#MAX_CAPACITY_1} for the second knapsack.
	 */
	public static List<Knapsack> createRandom() {
		final List<Item> remainingItems = new ArrayList<>(Problem.ITEMS);
		final Random random = Random.from(RandomRegistry.random());
		Collections.shuffle(remainingItems, random);
		final Items items0 = takeWhileWithinMaxCapacity(remainingItems, Problem.MAX_CAPACITY_0);
		final Items items1 = takeWhileWithinMaxCapacity(remainingItems, Problem.MAX_CAPACITY_1);
		return List.of(createFirst(items0), createSecond(items1));
	}

	private static Items takeWhileWithinMaxCapacity(final List<Item> remainingItems, final int maxCapacity) {
		final var items = new Items();
		var totalWeight = 0;
		for (final Item item : remainingItems) {
			final var itemWeight = item.weight();
			if (totalWeight + itemWeight <= maxCapacity) {
				items.add(item);
				totalWeight += itemWeight;
			}
		}
		remainingItems.removeAll(items);
		return items;
	}

}
