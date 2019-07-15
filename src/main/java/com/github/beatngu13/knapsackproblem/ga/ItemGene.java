package com.github.beatngu13.knapsackproblem.ga;

import com.github.beatngu13.knapsackproblem.Problem;
import com.github.beatngu13.knapsackproblem.base.Item;

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
	public Item getAllele() {
		return item;
	}

	@Override
	public ItemGene newInstance() {
		final var random = RandomRegistry.getRandom();
		final var index = random.nextInt(Problem.ITEMS.size());
		final var item = Problem.ITEMS.get(index);
		return new ItemGene(item);
	}

	@Override
	public ItemGene newInstance(final Item item) {
		return new ItemGene(item);
	}

}
