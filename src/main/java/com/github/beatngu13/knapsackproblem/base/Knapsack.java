package com.github.beatngu13.knapsackproblem.base;

import java.util.List;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.Problem;

import io.jenetics.util.RandomRegistry;
import lombok.Value;

@Value
public class Knapsack {

	private final List<Item> items;

	public static Knapsack newInstance() {
		final var random = RandomRegistry.getRandom();
		final var items = Problem.ITEMS.stream() //
				.filter(item -> random.nextBoolean()) //
				.collect(Collectors.toList());
		final var knapsack = new Knapsack(items);
		// Make sure only valid knapsacks are created.
		return knapsack.getWeight() <= Problem.MAX_CAPACITY ? knapsack : newInstance();
	}

	public int getProfit() {
		return items.stream() //
				.mapToInt(Item::getProfit) //
				.sum();
	}

	public int getWeight() {
		return items.stream() //
				.mapToInt(Item::getWeight) //
				.sum();
	}

	@Override
	public String toString() {
		return "Knapsack [profit = " + getProfit() + ", weight = " + getWeight() + "]";
	}

}
