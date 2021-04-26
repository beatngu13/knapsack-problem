package com.github.beatngu13.knapsackproblem.mo;

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
import io.jenetics.util.IntRange;
import io.jenetics.util.RandomRegistry;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MultiObjectiveIT {

	@Test
	void should_find_optimal_solution() {
		final var knapsackEngine = Engine.builder(new ProfitFitness(), new KnapsackCodec())
				.executor(Runnable::run) // Single-threaded for reproducibility.
				.selector(NSGA2Selector.ofVec())
				.alterers(new SinglePointCrossover<>(0.2), new Mutator<>(0.15), new UnusedItemsMutator(0.3))
				.constraint(new WeightAndItemsConstraint())
				.build();

		final EvolutionStatistics<Vec<int[]>, ?> stats = EvolutionStatistics.ofComparable();

		final var paretoSet = RandomRegistry.with(new Random(1L), // Fixed seed for reproducibility.
				rand -> knapsackEngine.stream()
						.limit(Limits.byFixedGeneration(300L))
						.peek(stats)
						.collect(MOEA.toParetoSet(IntRange.of(20, 50))));

		final var optimalSolution = Collections.max(paretoSet.asList(),
				Comparator.comparing(MultiObjectiveIT::getProfit));
		assertThat(getKnapsacks(optimalSolution)).containsExactlyInAnyOrder(
				MultiObjectiveProblem.OPTIMAL_KNAPSACK_0,
				MultiObjectiveProblem.OPTIMAL_KNAPSACK_1);
		System.out.println(stats);
	}

	private static int getProfit(final Phenotype<ItemGene, Vec<int[]>> phenotype) {
		final Vec<int[]> profit = phenotype.fitness();
		return Arrays.stream(profit.data()).sum();
	}

	private static Stream<Knapsack> getKnapsacks(final Phenotype<ItemGene, Vec<int[]>> phenotype) {
		return phenotype.genotype().stream()
				.map(KnapsackChromosome.class::cast)
				.map(KnapsackChromosome::knapsack);
	}

}
