package com.github.beatngu13.knapsackproblem.mo.ga;

import java.util.List;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;
import io.jenetics.ext.moea.Vec;

public class WeightConstraint implements Constraint<ItemGene, Vec<int[]>> {

	@Override
	public boolean test(final Phenotype<ItemGene, Vec<int[]>> individual) {
		final boolean valid = !hasRepeatedItems(individual);
		return valid && individual.getGenotype().stream() //
				.map(i -> ((KnapsackChromosome) i).getKnapsack()) //
				.allMatch(k -> k.getWeight() <= k.getMaxCapacity());
	}

	@Override
	public Phenotype<ItemGene, Vec<int[]>> repair(final Phenotype<ItemGene, Vec<int[]>> individual,
			final long generation) {
		final var knapsacks = Knapsack.newInstances();
		final var kc0 = new KnapsackChromosome(knapsacks.get(0));
		final var kc1 = new KnapsackChromosome(knapsacks.get(1));
		final var g = Genotype.of(kc0, kc1);
		return Phenotype.of(g, generation);
	}

	private boolean hasRepeatedItems(final Phenotype<ItemGene, Vec<int[]>> individual) {
		final List<Chromosome<ItemGene>> knapsacks = individual.getGenotype().toSeq().asList();
		final List<Item> knapsack1 = knapsacks.get(0).stream() //
				.map(i -> i.getItem()) //
				.collect(Collectors.toList());
		final List<Item> knapsack2 = knapsacks.get(1).stream() //
				.map(i -> i.getItem()) //
				.collect(Collectors.toList());
		return knapsack1.stream() //
				.anyMatch(element -> knapsack2.contains(element));
	}
}
