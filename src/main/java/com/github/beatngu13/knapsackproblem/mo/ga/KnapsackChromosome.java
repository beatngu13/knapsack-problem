package com.github.beatngu13.knapsackproblem.mo.ga;

import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;

import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;
import lombok.Value;

@Value
public class KnapsackChromosome implements Chromosome<ItemGene> {

	private final Knapsack knapsack;

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
		final var items = genes.stream() //
				.map(ItemGene::allele) //
				.collect(Collectors.toSet());
		return new KnapsackChromosome(KnapsackFactory.create(items, knapsack.maxCapacity()));
	}

	@Override
	public ItemGene get(final int index) {
		return knapsack.items().stream() //
				.map(ItemGene::new) //
				.collect(Collectors.toList()) //
				.get(index);
	}

	@Override
	public int length() {
		return knapsack.items().size();
	}

}
