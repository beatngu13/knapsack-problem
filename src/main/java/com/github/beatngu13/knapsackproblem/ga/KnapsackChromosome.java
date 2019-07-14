package com.github.beatngu13.knapsackproblem.ga;

import java.util.Iterator;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.Problem;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;
import lombok.Value;
import lombok.val;

@Value
public class KnapsackChromosome implements Chromosome<ItemGene> {

	private final Knapsack knapsack;

	@Override
	public boolean isValid() {
		return knapsack.getWeight() <= Problem.MAX_CAPACITY;
	}

	@Override
	public Iterator<ItemGene> iterator() {
		return toSeq().iterator();
	}

	@Override
	public Chromosome<ItemGene> newInstance() {
		return new KnapsackChromosome(Knapsack.newInstance());
	}

	@Override
	public Chromosome<ItemGene> newInstance(ISeq<ItemGene> genes) {
		final val items = genes.stream() //
				.map(ItemGene::getAllele) //
				.collect(Collectors.toSet());
		return new KnapsackChromosome(new Knapsack(items));
	}

	@Override
	public ItemGene getGene(int index) {
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
