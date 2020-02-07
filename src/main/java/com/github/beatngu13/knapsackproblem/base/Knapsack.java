package com.github.beatngu13.knapsackproblem.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;
import com.github.beatngu13.knapsackproblem.so.SingeObjectiveProblem;

import io.jenetics.util.RandomRegistry;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * A knapsack, consisting of a set of {@link Item}s.
 */
@Value
@AllArgsConstructor
public class Knapsack {

	/**
	 * The set of items in this knapsack.
	 */
	private final Set<Item> items;

	/**
	 * The maximum capacity of the knapsack.
	 */
	private final int maxCapacity;

	public Knapsack(final Set<Item> items) {
		this(items, SingeObjectiveProblem.MAX_CAPACITY);
	}

	/**
	 * @return A new instance with random items from
	 *         {@link SingeObjectiveProblem#ITEMS}.
	 */
	public static Knapsack newInstance() {
		final var random = RandomRegistry.getRandom();
		final var items = SingeObjectiveProblem.ITEMS.stream() //
				.filter(item -> random.nextBoolean()) //
				.collect(Collectors.toSet());
		final var knapsack = new Knapsack(items);

		return knapsack.isWithinMaxCapacity() ? knapsack : newInstance();
	}

	/**
	 * @param maxCapacity The maximum capacity of the knapsack.
	 * @return A new instance with random items from
	 *         {@link MultiObjectiveProblem#ITEMS}.
	 */
	public static Knapsack newInstance(final int maxCapacity) {
		final var random = RandomRegistry.getRandom();
		final var items = MultiObjectiveProblem.ITEMS.stream() //
				.filter(item -> random.nextBoolean()) //
				.collect(Collectors.toSet());
		final var knapsack = new Knapsack(items, maxCapacity);

		return knapsack.isWithinMaxCapacity() ? knapsack : newInstance(maxCapacity);
	}

	/**
	 * @param items       The set of items for the knapsack.
	 * @param maxCapacity The maximum capacity of the knapsack.
	 * @return A new instance with random items from
	 *         {@link MultiObjectiveProblem#ITEMS}.
	 */
	public static Knapsack newInstance(final Set<Item> items, final int maxCapacity) {
		final var knapsack = new Knapsack(items, maxCapacity);

		return knapsack.isWithinMaxCapacity() ? knapsack : newInstance(maxCapacity);
	}

	/**
	 * @return A list of new instances with randomly selected, mutually exclusive
	 *         items from {@link MultiObjectiveProblem#ITEMS}.
	 */
	public static List<Knapsack> newInstances() {
		final List<Item> remainingItems = new ArrayList<>(MultiObjectiveProblem.ITEMS);
		Collections.shuffle(remainingItems, RandomRegistry.getRandom());

		final Set<Item> items0 = takeWhileWithinMaxCapacity(remainingItems, MultiObjectiveProblem.MAX_CAPACITY_0);
		final Set<Item> items1 = takeWhileWithinMaxCapacity(remainingItems, MultiObjectiveProblem.MAX_CAPACITY_1);

		return Arrays.asList(new Knapsack(items0, MultiObjectiveProblem.MAX_CAPACITY_0),
				new Knapsack(items1, MultiObjectiveProblem.MAX_CAPACITY_1));
	}

	private static Set<Item> takeWhileWithinMaxCapacity(final List<Item> remainingItems, final int maxCapacity) {
		final var items = new HashSet<Item>();
		var totalWeight = 0;

		for (final Item item : remainingItems) {
			final var itemWeight = item.getWeight();
			if (totalWeight + itemWeight <= maxCapacity) {
				items.add(item);
				totalWeight += itemWeight;
			}
		}

		remainingItems.removeAll(items);

		return items;
	}

	/**
	 * @return The summarized profit of all items in this knapsack.
	 */
	public int getProfit() {
		return items.stream() //
				.mapToInt(Item::getProfit) //
				.sum();
	}

	/**
	 * @return The summarized weight of all items in this knapsack.
	 */
	public int getWeight() {
		return items.stream() //
				.mapToInt(Item::getWeight) //
				.sum();
	}

	/**
	 * @return <code>true</code> if the summarized weight of all items does not
	 *         exceed the maximum capacity.
	 */
	public boolean isWithinMaxCapacity() {
		return getWeight() <= maxCapacity;
	}

	@Override
	public String toString() {
		return "Knapsack(profit=" + getProfit() + ", weight=" + getWeight() + ", max capacity=" + getMaxCapacity()
				+ ")";
	}

}
