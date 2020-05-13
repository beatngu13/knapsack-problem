package com.github.beatngu13.knapsackproblem.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;
import com.github.beatngu13.knapsackproblem.so.SingleObjectiveProblem;

import io.jenetics.util.RandomRegistry;

public class KnapsackFactory {

	private KnapsackFactory() {

	}

	// GENERIC

	/**
	 * @param items       The set of items.
	 * @param maxCapacity The maximum capacity.
	 * @return A new knapsack with the given set of items and maximum capacity.
	 */
	public static Knapsack create(final Set<Item> items, final int maxCapacity) {
		return new Knapsack(items, maxCapacity);
	}

	// SINGLE OBJECTIVE

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 *         {@link SingleObjectiveProblem#MAX_CAPACITY}.
	 */
	public static Knapsack createSO(final Set<Item> items) {
		return create(items, SingleObjectiveProblem.MAX_CAPACITY);
	}

	/**
	 * @return A new knapsack with random items from
	 *         {@link SingleObjectiveProblem#ITEMS} and a maximum capacity of
	 *         {@link SingleObjectiveProblem#MAX_CAPACITY}.
	 */
	public static Knapsack createRandomSO() {
		final var random = RandomRegistry.random();
		final var items = SingleObjectiveProblem.ITEMS.stream() //
				.filter(item -> random.nextBoolean()) //
				.collect(Collectors.toSet());
		final var knapsack = createSO(items);
		return knapsack.isWithinMaxCapacity() ? knapsack : createRandomSO();
	}

	// MULTI OBJECTIVE

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 *         {@link MultiObjectiveProblem#MAX_CAPACITY_0}.
	 */
	public static Knapsack createMO0(final Set<Item> items) {
		return create(items, MultiObjectiveProblem.MAX_CAPACITY_0);
	}

	/**
	 * @param items The set of items.
	 * @return A new knapsack with the given set of items and a maximum capacity of
	 *         {@link MultiObjectiveProblem#MAX_CAPACITY_1}.
	 */
	public static Knapsack createMO1(final Set<Item> items) {
		return create(items, MultiObjectiveProblem.MAX_CAPACITY_1);
	}

	/**
	 * @return A new knapsack with random items from
	 *         {@link MultiObjectiveProblem#ITEMS} and the given maximum capacity.
	 */
	public static Knapsack createRandomMO(final int maxCapacity) {
		final var random = RandomRegistry.random();
		final var items = MultiObjectiveProblem.ITEMS.stream() //
				.filter(item -> random.nextBoolean()) //
				.collect(Collectors.toSet());
		final var knapsack = create(items, maxCapacity);
		return knapsack.isWithinMaxCapacity() ? knapsack : createRandomMO(maxCapacity);
	}

	/**
	 * @return A list of new knapsacks with random, mutually exclusive items from
	 *         {@link MultiObjectiveProblem#ITEMS} and a maximum capacity of
	 *         {@link MultiObjectiveProblem#MAX_CAPACITY_0} for the first and
	 *         {@link MultiObjectiveProblem#MAX_CAPACITY_1} for the second knapsack.
	 */
	public static List<Knapsack> createRandomMO() {
		final List<Item> remainingItems = new ArrayList<>(MultiObjectiveProblem.ITEMS);
		Collections.shuffle(remainingItems, RandomRegistry.random());
		final Set<Item> items0 = takeWhileWithinMaxCapacity(remainingItems, MultiObjectiveProblem.MAX_CAPACITY_0);
		remainingItems.removeAll(items0);
		final Set<Item> items1 = takeWhileWithinMaxCapacity(remainingItems, MultiObjectiveProblem.MAX_CAPACITY_1);
		return List.of(createMO0(items0), createMO1(items1));
	}

	private static Set<Item> takeWhileWithinMaxCapacity(final List<Item> remainingItems, final int maxCapacity) {
		final var items = new HashSet<Item>();
		var totalWeight = 0;
		for (final Item item : remainingItems) {
			final var itemWeight = item.weight();
			if (totalWeight + itemWeight <= maxCapacity) {
				items.add(item);
				totalWeight += itemWeight;
			}
		}
		return items;
	}

}
