package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.mo.Problem;
import io.jenetics.Gene;
import io.jenetics.util.RandomRegistry;

public record ItemGene(Item item) implements Gene<Item, ItemGene> {

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
		final var index = random.nextInt(Problem.ITEMS.size());
		final var item = Problem.ITEMS.get(index);
		return new ItemGene(item);
	}

	@Override
	public ItemGene newInstance(final Item item) {
		return new ItemGene(item);
	}

}
