package com.github.beatngu13.knapsackproblem.base;

import java.util.List;

import lombok.Value;

@Value
public class Knapsack {

	List<Item> items;

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
