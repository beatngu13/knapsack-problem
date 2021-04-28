package com.github.beatngu13.knapsackproblem.so.ga;

import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;

public class WeightConstraint implements Constraint<ItemGene, Integer> {

	@Override
	public boolean test(final Phenotype<ItemGene, Integer> individual) {
		return individual.genotype()
				.chromosome()
				.as(KnapsackChromosome.class)
				.knapsack()
				.isWithinMaxCapacity();
	}

	@Override
	public Phenotype<ItemGene, Integer> repair(final Phenotype<ItemGene, Integer> individual, final long generation) {
		// No repair like Constraint#of(Predicate).
		return Phenotype.of(individual.genotype().newInstance(), 0);
	}

}
