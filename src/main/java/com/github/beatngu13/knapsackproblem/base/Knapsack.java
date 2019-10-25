package com.github.beatngu13.knapsackproblem.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		// Make sure only valid knapsacks are created.
		return knapsack.getWeight() <= SingeObjectiveProblem.MAX_CAPACITY ? knapsack : newInstance();
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

		return knapsack.getWeight() <= maxCapacity ? knapsack : newInstance(maxCapacity);
	}

	/**
	 * @param items       The set of items for the knapsack.
	 * @param maxCapacity The maximum capacity of the knapsack.
	 * @return A new instance with random items from
	 *         {@link MultiObjectiveProblem#ITEMS}.
	 */
	public static Knapsack newInstance(final Set<Item> items, final int maxCapacity) {
		final var knapsack = new Knapsack(items, maxCapacity);

		return knapsack.getWeight() <= maxCapacity ? knapsack : newInstance(maxCapacity);
	}

	/**
	 * @return A list of new instances with randomly selected, mutually exclusive
	 *         items from {@link MultiObjectiveProblem#ITEMS}.
	 */
	public static List<Knapsack> newInstances() {
		final List<Item> shuffledItems = new ArrayList<>(MultiObjectiveProblem.ITEMS);
		Collections.shuffle(shuffledItems);

		final IntPredicate isEven = (i) -> i % 2 == 0;
		final IntPredicate isOdd = isEven.negate();

		final Set<Item> setOfItems0 = generateSetBasedOnPredicate(shuffledItems, isEven,
				MultiObjectiveProblem.MAX_CAPACITY_0);
		final Set<Item> setOfItems1 = generateSetBasedOnPredicate(shuffledItems, isOdd,
				MultiObjectiveProblem.MAX_CAPACITY_1);

		return Arrays.asList(new Knapsack(setOfItems0, MultiObjectiveProblem.MAX_CAPACITY_0),
				new Knapsack(setOfItems1, MultiObjectiveProblem.MAX_CAPACITY_1));
	}

	private static Set<Item> generateSetBasedOnPredicate(final List<Item> allItems, final IntPredicate predicate,
			final int maxCapacity) {
		final Set<Item> setOfItems = new HashSet<>();

		IntStream.range(0, allItems.size()) //
				.filter(predicate) //
				.forEach(i -> {
					final Item currentItem = allItems.get(i);
					final int totalWeight = setOfItems.stream() //
							.mapToInt(item -> item.getWeight()) //
							.sum() //
							+ currentItem.getWeight();
					if (totalWeight <= maxCapacity) {
						setOfItems.add(currentItem);
					}
				});

		return setOfItems;
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

	@Override
	public String toString() {
		return "Knapsack(profit=" + getProfit() + ", weight=" + getWeight() + ", max capacity=" + getMaxCapacity()
				+ ")";
	}

}
