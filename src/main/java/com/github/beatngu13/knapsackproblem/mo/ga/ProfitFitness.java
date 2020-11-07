package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import io.jenetics.ext.moea.Vec;
import io.jenetics.util.ISeq;

import java.util.function.Function;

public class ProfitFitness implements Function<ISeq<Knapsack>, Vec<int[]>> {

	@Override
	public Vec<int[]> apply(final ISeq<Knapsack> knapsacks) {
		final var profits = knapsacks.stream()
				.map(Knapsack::profit)
				.mapToInt(Integer::intValue)
				.toArray();
		return Vec.of(profits);
	}

}
