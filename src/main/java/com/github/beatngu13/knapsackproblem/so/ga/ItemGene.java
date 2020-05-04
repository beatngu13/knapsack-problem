package com.github.beatngu13.knapsackproblem.so.ga;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.so.SingleObjectiveProblem;

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
		final var index = random.nextInt(SingleObjectiveProblem.ITEMS.size());
		final var item = SingleObjectiveProblem.ITEMS.get(index);
		return new ItemGene(item);
	}

	@Override
	public ItemGene newInstance(final Item item) {
		return new ItemGene(item);
	}

}
