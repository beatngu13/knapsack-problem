package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;

import io.jenetics.Gene;
import io.jenetics.util.RandomRegistry;
import lombok.Value;

@Value
public class ItemGene implements Gene<Item, ItemGene> {

	private final Item item;

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public Item allele() {
		return item;
	}

	@Override
	public ItemGene newInstance() {
		final var random = RandomRegistry.random();
		final var index = random.nextInt(MultiObjectiveProblem.ITEMS.size());
		final var item = MultiObjectiveProblem.ITEMS.get(index);
		return new ItemGene(item);
	}

	@Override
	public ItemGene newInstance(final Item item) {
		return new ItemGene(item);
	}

}
