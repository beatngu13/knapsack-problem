package com.github.beatngu13.knapsackproblem.so.ga;

import java.util.function.Function;

import com.github.beatngu13.knapsackproblem.base.Knapsack;

public class ProfitFitness implements Function<Knapsack, Integer> {

	@Override
	public Integer apply(final Knapsack knapsack) {
		return knapsack.profit();
	}

}
