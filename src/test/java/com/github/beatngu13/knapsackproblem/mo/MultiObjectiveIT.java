package com.github.beatngu13.knapsackproblem.mo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.mo.ga.ItemGene;
import com.github.beatngu13.knapsackproblem.mo.ga.KnapsackChromosome;
import com.github.beatngu13.knapsackproblem.mo.ga.KnapsackCodec;
import com.github.beatngu13.knapsackproblem.mo.ga.ProfitFitness;
import com.github.beatngu13.knapsackproblem.mo.ga.UnusedItemsMutator;
import com.github.beatngu13.knapsackproblem.mo.ga.WeightAndItemsConstraint;

import io.jenetics.Mutator;
import io.jenetics.Phenotype;
import io.jenetics.SinglePointCrossover;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.engine.Limits;
import io.jenetics.ext.moea.MOEA;
import io.jenetics.ext.moea.NSGA2Selector;
import io.jenetics.ext.moea.Vec;
import io.jenetics.util.ISeq;
import io.jenetics.util.IntRange;

class MultiObjectiveIT {

	@Test
	void should_find_optimal_solution() throws Exception {
		final Engine<ItemGene, Vec<int[]>> knapsackEngine = Engine.builder(new ProfitFitness(), new KnapsackCodec()) //
				.selector(NSGA2Selector.ofVec()) //
				.alterers(new SinglePointCrossover<>(0.2), new Mutator<>(0.15), new UnusedItemsMutator(0.3)) //
				.constraint(new WeightAndItemsConstraint()) //
				.build();

		final EvolutionStatistics<Vec<int[]>, ?> stats = EvolutionStatistics.ofComparable();

		final ISeq<Phenotype<ItemGene, Vec<int[]>>> paretoSet = knapsackEngine.stream() //
				.limit(Limits.byFixedGeneration(300L)) //
				.peek(stats) //
				.collect(MOEA.toParetoSet(IntRange.of(20, 50)));

		final Phenotype<ItemGene, Vec<int[]>> optimalSolution = Collections.max(paretoSet.asList(),
				Comparator.comparing(MultiObjectiveIT::getPhenotypeProfit));

		final var bestKnapsack0 = getKnapsack(optimalSolution, 0);
		final var bestKnapsack1 = getKnapsack(optimalSolution, 1);
		assertThat(bestKnapsack0).isEqualTo(MultiObjectiveProblem.OPTIMAL_KNAPSACK_0);
		assertThat(bestKnapsack1).isEqualTo(MultiObjectiveProblem.OPTIMAL_KNAPSACK_1);
		System.out.println(stats);
	}

	private static int getPhenotypeProfit(final Phenotype<ItemGene, Vec<int[]>> phenotype) {
		final Vec<int[]> profit = phenotype.getFitness();
		return Arrays.stream(profit.data()).sum();
	}

	private static Knapsack getKnapsack(final Phenotype<ItemGene, Vec<int[]>> phenotype, final int index) {
		return ((KnapsackChromosome) phenotype.getGenotype().getChromosome(index)).getKnapsack();
	}

}
