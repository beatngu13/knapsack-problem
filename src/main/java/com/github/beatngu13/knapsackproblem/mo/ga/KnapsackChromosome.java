package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveKnapsackFactory;
import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;

import java.util.stream.Collectors;

public record KnapsackChromosome(Knapsack knapsack) implements Chromosome<ItemGene> {

	@Override
	public boolean isValid() {
		return knapsack.isWithinMaxCapacity();
	}

	@Override
	public Chromosome<ItemGene> newInstance() {
		return new KnapsackChromosome(MultiObjectiveKnapsackFactory.createRandom(knapsack.maxCapacity()));
	}

	@Override
	public Chromosome<ItemGene> newInstance(final ISeq<ItemGene> genes) {
		final var items = genes.stream()
				.map(ItemGene::allele)
				.collect(Collectors.toCollection(Items::new));
		return new KnapsackChromosome(new Knapsack(items, knapsack.maxCapacity()));
	}

	@Override
	public ItemGene get(final int index) {
		return knapsack.items().stream()
				.map(ItemGene::new)
				.toList()
				.get(index);
	}

	@Override
	public int length() {
		return knapsack.items().size();
	}

}
