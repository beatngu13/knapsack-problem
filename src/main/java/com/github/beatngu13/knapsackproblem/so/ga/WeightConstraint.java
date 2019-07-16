package com.github.beatngu13.knapsackproblem.so.ga;

import com.github.beatngu13.knapsackproblem.so.SingeObjectiveProblem;

import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;

public class WeightConstraint implements Constraint<ItemGene, Integer> {

	@Override
	public boolean test(final Phenotype<ItemGene, Integer> individual) {
		final var knapsack = ((KnapsackChromosome) individual.getGenotype().getChromosome()).getKnapsack();
		return knapsack.getWeight() <= SingeObjectiveProblem.MAX_CAPACITY;
	}

	@Override
	public Phenotype<ItemGene, Integer> repair(final Phenotype<ItemGene, Integer> individual, final long generation) {
		// No repair like Constraint#of(Predicate).
		return Phenotype.of(individual.getGenotype().newInstance(), generation);
	}

}
