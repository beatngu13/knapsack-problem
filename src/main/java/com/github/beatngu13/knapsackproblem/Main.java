package com.github.beatngu13.knapsackproblem;

import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

public class Main {

	public static void main(final String[] args) {
		final var knapsackEngine = Engine.builder(new ProfitFitness(), new KnapsackCodec()) //
				.constraint(new WeightConstraint()) //
				.build();
		final var bestPhenotype = knapsackEngine.stream() //
				.limit(100L) //
				.collect(EvolutionResult.toBestPhenotype());
		final var bestKnapsack = bestPhenotype.getGenotype().getGene().getAllele();
		System.out.println("Solution: " + bestKnapsack);
		System.out.println("Optimum:  " + Problem.OPTIMAL_KNAPSACK);
	}

}
