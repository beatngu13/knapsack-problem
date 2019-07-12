package com.github.beatngu13.knapsackproblem;

import java.util.function.Function;

public class ProfitFitness implements Function<Knapsack, Integer> {

	@Override
	public Integer apply(final Knapsack knapsack) {
		return knapsack.getProfit();
	}

}
