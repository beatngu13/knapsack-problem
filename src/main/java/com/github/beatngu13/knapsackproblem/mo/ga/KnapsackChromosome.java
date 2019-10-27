package com.github.beatngu13.knapsackproblem.mo.ga;

import java.util.Iterator;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.base.Knapsack;

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
	public Iterator<ItemGene> iterator() {
		return toSeq().iterator();
	}

	@Override
	public Chromosome<ItemGene> newInstance() {
		return new KnapsackChromosome(Knapsack.newInstance(knapsack.getMaxCapacity()));
	}

	@Override
	public Chromosome<ItemGene> newInstance(final ISeq<ItemGene> genes) {
		final var items = genes.stream() //
				.map(ItemGene::getAllele) //
				.collect(Collectors.toSet());
		return new KnapsackChromosome(new Knapsack(items, knapsack.getMaxCapacity()));
	}

	@Override
	public ItemGene getGene(final int index) {
		return toSeq().get(index);
	}

	@Override
	public int length() {
		return knapsack.getItems().size();
	}

	@Override
	public ISeq<ItemGene> toSeq() {
		return knapsack.getItems().stream() //
				.map(ItemGene::new) //
				.collect(ISeq.toISeq());
	}

}
