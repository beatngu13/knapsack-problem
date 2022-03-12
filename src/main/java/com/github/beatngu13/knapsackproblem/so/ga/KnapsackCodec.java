package com.github.beatngu13.knapsackproblem.so.ga;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.so.KnapsackFactory;
import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.util.Factory;

import java.util.function.Function;

public class KnapsackCodec implements Codec<Knapsack, ItemGene> {

	@Override
	public Factory<Genotype<ItemGene>> encoding() {
		return Genotype.of(new KnapsackChromosome(KnapsackFactory.createRandom()));
	}

	@Override
	public Function<Genotype<ItemGene>, Knapsack> decoder() {
		return genotype -> genotype.chromosome()
				.as(KnapsackChromosome.class)
				.knapsack();
	}

}
