package com.github.beatngu13.knapsackproblem.ga;

import com.github.beatngu13.knapsackproblem.Problem;
import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import io.jenetics.AnyGene;
import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;

public class WeightConstraint implements Constraint<AnyGene<Knapsack>, Integer> {

	@Override
	public boolean test(final Phenotype<AnyGene<Knapsack>, Integer> individual) {
		final var knapsack = individual.getGenotype().getGene().getAllele();
		final var weight = knapsack.getItems().stream() //
				.mapToInt(Item::getWeight) //
				.sum();
		return weight <= Problem.MAX_CAPACITY;
	}

	@Override
	public Phenotype<AnyGene<Knapsack>, Integer> repair(final Phenotype<AnyGene<Knapsack>, Integer> individual,
			final long generation) {
		// No repair like Constraint#of(Predicate).
		return Phenotype.of(individual.getGenotype().newInstance(), generation);
	}

}
