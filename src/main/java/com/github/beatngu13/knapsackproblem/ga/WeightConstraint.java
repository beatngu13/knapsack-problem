package com.github.beatngu13.knapsackproblem.ga;

import com.github.beatngu13.knapsackproblem.Problem;

import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;

public class WeightConstraint implements Constraint<ItemGene, Integer> {

	@Override
	public boolean test(Phenotype<ItemGene, Integer> individual) {
		final var knapsack = ((KnapsackChromosome) individual.getGenotype().getChromosome()).getKnapsack();
		return knapsack.getWeight() <= Problem.MAX_CAPACITY;
	}

	@Override
	public Phenotype<ItemGene, Integer> repair(Phenotype<ItemGene, Integer> individual, long generation) {
		// No repair like Constraint#of(Predicate).
		return Phenotype.of(individual.getGenotype().newInstance(), generation);
	}

}
