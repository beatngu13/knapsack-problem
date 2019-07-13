package com.github.beatngu13.knapsackproblem.ga;

import com.github.beatngu13.knapsackproblem.Problem;
import com.github.beatngu13.knapsackproblem.base.Item;

import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;

public class WeightConstraint implements Constraint<ItemGene, Integer> {

	@Override
	public boolean test(Phenotype<ItemGene, Integer> individual) {
		final var knapsack = ((KnapsackChromosome) individual.getGenotype().getChromosome()).getKnapsack();
		final var weight = knapsack.getItems().stream() //
				.mapToInt(Item::getWeight) //
				.sum();
		return weight <= Problem.MAX_CAPACITY;
	}

	@Override
	public Phenotype<ItemGene, Integer> repair(Phenotype<ItemGene, Integer> individual, long generation) {
		// No repair like Constraint#of(Predicate).
		return Phenotype.of(individual.getGenotype().newInstance(), generation);
	}

}
