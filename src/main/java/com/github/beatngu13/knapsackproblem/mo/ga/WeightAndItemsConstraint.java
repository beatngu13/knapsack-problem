package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.mo.KnapsackFactory;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;
import io.jenetics.ext.moea.Vec;

import java.util.Collections;

public class WeightAndItemsConstraint implements Constraint<ItemGene, Vec<int[]>> {

	@Override
	public boolean test(final Phenotype<ItemGene, Vec<int[]>> individual) {
		return hasWeightWithinMaxCapacity(individual) && hasMutuallyExclusiveItems(individual);
	}

	private boolean hasWeightWithinMaxCapacity(final Phenotype<ItemGene, Vec<int[]>> individual) {
		return individual.genotype().stream()
				.map(KnapsackChromosome.class::cast)
				.map(KnapsackChromosome::knapsack)
				.allMatch(Knapsack::isWithinMaxCapacity);
	}

	private boolean hasMutuallyExclusiveItems(final Phenotype<ItemGene, Vec<int[]>> individual) {
		final var knapsacks = individual.genotype();
		final var knapsack0 = knapsacks.get(0)
				.as(KnapsackChromosome.class)
				.knapsack();
		final var knapsack1 = knapsacks.get(1)
				.as(KnapsackChromosome.class)
				.knapsack();
		return Collections.disjoint(knapsack0.items(), knapsack1.items());
	}

	@Override
	public Phenotype<ItemGene, Vec<int[]>> repair(final Phenotype<ItemGene, Vec<int[]>> individual,
												  final long generation) {
		// No repair like Constraint#of(Predicate).
		final var knapsacks = KnapsackFactory.createRandom();
		final var chromosome0 = new KnapsackChromosome(knapsacks.get(0));
		final var chromosome1 = new KnapsackChromosome(knapsacks.get(1));
		final var genotype = Genotype.of(chromosome0, chromosome1);
		return Phenotype.of(genotype, 0L);
	}

}
