package com.github.beatngu13.knapsackproblem.so;

import com.github.beatngu13.knapsackproblem.so.ga.ItemGene;
import com.github.beatngu13.knapsackproblem.so.ga.KnapsackChromosome;
import com.github.beatngu13.knapsackproblem.so.ga.KnapsackCodec;
import com.github.beatngu13.knapsackproblem.so.ga.ProfitFitness;
import com.github.beatngu13.knapsackproblem.so.ga.UnusedItemsMutator;
import com.github.beatngu13.knapsackproblem.so.ga.WeightConstraint;

import io.jenetics.Genotype;
import io.jenetics.Mutator;
import io.jenetics.SinglePointCrossover;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;

public class Main {

	public static void main(final String[] args) {
		final Engine<ItemGene, Integer> knapsackEngine = Engine.builder(new ProfitFitness(), new KnapsackCodec()) //
				.alterers(new SinglePointCrossover<>(0.2), new Mutator<>(0.15), new UnusedItemsMutator(0.3)) //
				.constraint(new WeightConstraint()) //
				.build();

		final EvolutionStatistics<Integer, ?> stats = EvolutionStatistics.ofNumber();

		final Genotype<ItemGene> bestPhenotype = knapsackEngine.stream() //
				.limit(100L) //
				.peek(stats) //
				.collect(EvolutionResult.toBestGenotype());

		final var bestKnapsack = ((KnapsackChromosome) bestPhenotype.getChromosome()).getKnapsack();
		System.out.println(stats);
		System.out.println("Solution: " + bestKnapsack);
		System.out.println("Optimum:  " + SingeObjectiveProblem.OPTIMAL_KNAPSACK);
	}

}
