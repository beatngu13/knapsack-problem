package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;
import com.github.beatngu13.knapsackproblem.util.Items;
import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;

public record KnapsackChromosome(Knapsack knapsack) implements Chromosome<ItemGene> {

	@Override
	public boolean isValid() {
		return knapsack.isWithinMaxCapacity();
	}

	@Override
	public Chromosome<ItemGene> newInstance() {
		return new KnapsackChromosome(KnapsackFactory.createRandomMO(knapsack.maxCapacity()));
	}

	@Override
	public Chromosome<ItemGene> newInstance(final ISeq<ItemGene> genes) {
		final var items = genes.stream()
				.map(ItemGene::allele)
				.collect(Items.collector());
		return new KnapsackChromosome(KnapsackFactory.create(items, knapsack.maxCapacity()));
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
