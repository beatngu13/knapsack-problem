package com.github.beatngu13.knapsackproblem.so.ga;

import java.util.function.Function;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;

import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.util.Factory;

public class KnapsackCodec implements Codec<Knapsack, ItemGene> {

	@Override
	public Factory<Genotype<ItemGene>> encoding() {
		return Genotype.of(new KnapsackChromosome(KnapsackFactory.createRandomSO()));
	}

	@Override
	public Function<Genotype<ItemGene>, Knapsack> decoder() {
		return genotype -> ((KnapsackChromosome) genotype.chromosome()).getKnapsack();
	}

}
