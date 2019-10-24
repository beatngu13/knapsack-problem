package com.github.beatngu13.knapsackproblem.mo.ga;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;

import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;

public class KnapsackCodec implements Codec<ISeq<Knapsack>, ItemGene> {

	@Override
	public Factory<Genotype<ItemGene>> encoding() {
		final Factory<Genotype<ItemGene>> factory = () -> {
			final List<Set<Item>> listOfMutuallyExclusiveSets = Knapsack.generateKnapsacks();
			final var kc0 = new KnapsackChromosome(
					Knapsack.newInstance(listOfMutuallyExclusiveSets.get(0), MultiObjectiveProblem.MAX_CAPACITY_0));
			final var kc1 = new KnapsackChromosome(
					Knapsack.newInstance(listOfMutuallyExclusiveSets.get(1), MultiObjectiveProblem.MAX_CAPACITY_1));
			return Genotype.of(kc0, kc1);
		};

		return factory;
	}

	@Override
	public Function<Genotype<ItemGene>, ISeq<Knapsack>> decoder() {
		return genotype -> {
			final ISeq<Knapsack> knapsacks = IntStream.range(0, MultiObjectiveProblem.NUMBER_OF_KNAPSACKS) //
					.mapToObj(i -> ((KnapsackChromosome) genotype.getChromosome(i)).getKnapsack()) //
					.collect(ISeq.toISeq());
			return knapsacks;
		};
	}

}
