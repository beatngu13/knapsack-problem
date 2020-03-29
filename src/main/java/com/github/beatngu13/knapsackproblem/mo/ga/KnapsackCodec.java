package com.github.beatngu13.knapsackproblem.mo.ga;

import java.util.function.Function;
import java.util.stream.IntStream;

import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;

import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;

public class KnapsackCodec implements Codec<ISeq<Knapsack>, ItemGene> {

	@Override
	public Factory<Genotype<ItemGene>> encoding() {
		return () -> {
			final var knapsacks = Knapsack.newInstances();
			final var kc0 = new KnapsackChromosome(knapsacks.get(0));
			final var kc1 = new KnapsackChromosome(knapsacks.get(1));
			return Genotype.of(kc0, kc1);
		};
	}

	@Override
	public Function<Genotype<ItemGene>, ISeq<Knapsack>> decoder() {
		return genotype -> {
			return IntStream.range(0, MultiObjectiveProblem.NUMBER_OF_KNAPSACKS) //
					.mapToObj(i -> ((KnapsackChromosome) genotype.get(i)).getKnapsack()) //
					.collect(ISeq.toISeq());
		};
	}

}
