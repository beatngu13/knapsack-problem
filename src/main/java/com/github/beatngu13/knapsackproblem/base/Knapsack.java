package com.github.beatngu13.knapsackproblem.base;

import java.util.Set;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.so.SingeObjectiveProblem;

import io.jenetics.util.RandomRegistry;
import lombok.Value;

/**
 * A knapsack, consisting of a set of {@link Item}s.
 */
@Value
public class Knapsack {

	/**
	 * The set of items in this knapsack.
	 */
	private final Set<Item> items;

	/**
	 * @return A new instance with random items from {@link SingeObjectiveProblem#ITEMS}.
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
		return "Knapsack(profit=" + getProfit() + ", weight=" + getWeight() + ")";
	}

}
