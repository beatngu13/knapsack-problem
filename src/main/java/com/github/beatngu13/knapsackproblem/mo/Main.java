package com.github.beatngu13.knapsackproblem.mo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.github.beatngu13.knapsackproblem.mo.ga.ItemGene;
import com.github.beatngu13.knapsackproblem.mo.ga.KnapsackChromosome;
import com.github.beatngu13.knapsackproblem.mo.ga.KnapsackCodec;
import com.github.beatngu13.knapsackproblem.mo.ga.ProfitFitness;
import com.github.beatngu13.knapsackproblem.mo.ga.UnusedItemsMutator;
import com.github.beatngu13.knapsackproblem.mo.ga.WeightConstraint;

import io.jenetics.Mutator;
import io.jenetics.Phenotype;
import io.jenetics.SinglePointCrossover;
import io.jenetics.engine.Engine;
import io.jenetics.engine.Limits;
import io.jenetics.ext.moea.MOEA;
import io.jenetics.ext.moea.Vec;
import io.jenetics.util.ISeq;
import io.jenetics.util.IntRange;

public class Main {
	public static void main(final String[] args) {
		final Engine<ItemGene, Vec<int[]>> knapsackEngine = Engine.builder(new ProfitFitness(), new KnapsackCodec()) //
				.alterers(new SinglePointCrossover<>(0.2), new Mutator<>(0.15), new UnusedItemsMutator(0.3)) //
				.constraint(new WeightConstraint()) //
				.build();

		final ISeq<Phenotype<ItemGene, Vec<int[]>>> paretoSet = knapsackEngine.stream() //
				.limit(Limits.byFixedGeneration(300)) //
				.collect(MOEA.toParetoSet(IntRange.of(20, 50)));

		final Phenotype<ItemGene, Vec<int[]>> optimalSolution = Collections.max(paretoSet.asList(),
				Comparator.comparing(phenotype -> calcTotalProfit(phenotype.getFitness())));

		System.out.println("Expected Optimum 0:  " + MultiObjectiveProblem.OPTIMAL_KNAPSACK_0);
		System.out.println("Expected Optimum 1:  " + MultiObjectiveProblem.OPTIMAL_KNAPSACK_1);
		System.out.println("");
		System.out.println("Optimum solution 0:  "
				+ ((KnapsackChromosome) optimalSolution.getGenotype().getChromosome(0)).getKnapsack());
		System.out.println("Optimum solution 1:  "
				+ ((KnapsackChromosome) optimalSolution.getGenotype().getChromosome(1)).getKnapsack());

	}

	private static int calcTotalProfit(final Vec<int[]> profit) {
		return Arrays.stream(profit.data()).sum();
	}
}