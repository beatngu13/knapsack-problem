package com.github.beatngu13.knapsackproblem.mo.ga;

import java.util.Collections;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;

import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;
import io.jenetics.ext.moea.Vec;

public class WeightAndItemsConstraint implements Constraint<ItemGene, Vec<int[]>> {

	@Override
	public boolean test(final Phenotype<ItemGene, Vec<int[]>> individual) {
		return hasWeightWithinMaxCapacity(individual) && hasMutuallyExclusiveItems(individual);
	}

	@Override
	public Phenotype<ItemGene, Vec<int[]>> repair(final Phenotype<ItemGene, Vec<int[]>> individual,
			final long generation) {
		final var knapsacks = KnapsackFactory.createRandomMO();
		final var chromosome0 = new KnapsackChromosome(knapsacks.get(0));
		final var chromosome1 = new KnapsackChromosome(knapsacks.get(1));
		final var genotype = Genotype.of(chromosome0, chromosome1);
		return Phenotype.of(genotype, generation);
	}

	private boolean hasWeightWithinMaxCapacity(final Phenotype<ItemGene, Vec<int[]>> individual) {
		return individual.genotype().stream() //
				.map(genotype -> ((KnapsackChromosome) genotype).getKnapsack()) //
				.allMatch(Knapsack::isWithinMaxCapacity);
	}

	private boolean hasMutuallyExclusiveItems(final Phenotype<ItemGene, Vec<int[]>> individual) {
		final var knapsacks = individual.genotype();
		final var knapsack0 = ((KnapsackChromosome) knapsacks.get(0)).getKnapsack();
		final var knapsack1 = ((KnapsackChromosome) knapsacks.get(1)).getKnapsack();
		return Collections.disjoint(knapsack0.getItems(), knapsack1.getItems());
	}

}
