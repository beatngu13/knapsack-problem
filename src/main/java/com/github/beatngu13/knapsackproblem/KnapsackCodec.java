package com.github.beatngu13.knapsackproblem;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jenetics.AnyChromosome;
import io.jenetics.AnyGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.util.Factory;
import io.jenetics.util.RandomRegistry;

public class KnapsackCodec implements Codec<Knapsack, AnyGene<Knapsack>> {

	@Override
	public Factory<Genotype<AnyGene<Knapsack>>> encoding() {
		return Genotype.of(AnyChromosome.of(KnapsackCodec::create));
	}

	private static Knapsack create() {
		final Random rand = RandomRegistry.getRandom();
		final List<Item> items = Problem.ITEMS.stream() //
				.filter(item -> rand.nextBoolean()) //
				.collect(Collectors.toList());
		final Knapsack knapsack = new Knapsack(items);
		// Make sure only valid solutions are created.
		return knapsack.getWeight() <= Problem.MAX_CAPACITY ? knapsack : create();
	}

	@Override
	public Function<Genotype<AnyGene<Knapsack>>, Knapsack> decoder() {
		return genotype -> genotype.getGene().getAllele();
	}

}
