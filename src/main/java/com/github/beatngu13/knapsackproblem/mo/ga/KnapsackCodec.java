package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.mo.KnapsackFactory;
import com.github.beatngu13.knapsackproblem.mo.Problem;
import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;

import java.util.function.Function;
import java.util.stream.IntStream;

public class KnapsackCodec implements Codec<ISeq<Knapsack>, ItemGene> {

	@Override
	public Factory<Genotype<ItemGene>> encoding() {
		return () -> {
			final var knapsacks = KnapsackFactory.createRandom();
			final var kc0 = new KnapsackChromosome(knapsacks.get(0));
			final var kc1 = new KnapsackChromosome(knapsacks.get(1));
			return Genotype.of(kc0, kc1);
		};
	}

	@Override
	public Function<Genotype<ItemGene>, ISeq<Knapsack>> decoder() {
		return genotype -> IntStream.range(0, Problem.NUMBER_OF_KNAPSACKS)
				.mapToObj(genotype::get)
				.map(KnapsackChromosome.class::cast)
				.map(KnapsackChromosome::knapsack)
				.collect(ISeq.toISeq());
	}

}
