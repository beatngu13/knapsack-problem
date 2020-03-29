package com.github.beatngu13.knapsackproblem.so.ga;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.so.SingeObjectiveProblem;

import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.util.Seq;

class UnusedItemsMutatorTest {

	@Test
	void should_not_mutate_when_probability_is_zero() throws Exception {
		final var knapsack = new Knapsack(Collections.emptySet());
		final var cut = new UnusedItemsMutator(0.0);

		final var result = cut.alter(toPhenotypeSeq(knapsack), 1L);

		final var knapsacks = toKnapsackList(result.population());
		assertThat(knapsacks).size().isOne();
		assertThat(knapsacks).first().isEqualTo(knapsack);
	}

	@Test
	void should_not_mutate_knapsack_without_available_weight() throws Exception {
		final var cut = new UnusedItemsMutator(1.0);

		final var result = cut.alter(toPhenotypeSeq(SingeObjectiveProblem.OPTIMAL_KNAPSACK), 1L);

		final var knapsacks = toKnapsackList(result.population());
		assertThat(knapsacks).size().isOne();
		assertThat(knapsacks).first().isEqualTo(SingeObjectiveProblem.OPTIMAL_KNAPSACK);
	}

	@Test
	void should_mutate_knapsack_with_available_weight() throws Exception {
		final var knapsack = new Knapsack(Collections.emptySet());
		final var cut = new UnusedItemsMutator(1.0);

		final var result = cut.alter(toPhenotypeSeq(knapsack), 1L);

		final var knapsacks = toKnapsackList(result.population());
		// Previously unused items, sorted by profit.
		final var expected = new Knapsack(
				new HashSet<Item>(SingeObjectiveProblem.ITEMS.subList(9, SingeObjectiveProblem.ITEMS.size())));
		assertThat(knapsacks).size().isOne();
		assertThat(knapsacks).first().isEqualTo(expected);
	}

	private static Seq<Phenotype<ItemGene, Integer>> toPhenotypeSeq(final Knapsack... knapsacks) {
		return Arrays.stream(knapsacks).map(UnusedItemsMutatorTest::toPhenotype).collect(Seq.toSeq());
	}

	private static Phenotype<ItemGene, Integer> toPhenotype(final Knapsack knapsack) {
		return Phenotype.of(Genotype.of(new KnapsackChromosome(knapsack)), 1L);
	}

	private static List<Knapsack> toKnapsackList(final Seq<Phenotype<ItemGene, Integer>> population) {
		return population.map(UnusedItemsMutatorTest::toKnapsack).asList();
	}

	private static Knapsack toKnapsack(final Phenotype<ItemGene, Integer> individual) {
		return ((KnapsackChromosome) individual.genotype().chromosome()).getKnapsack();
	}

}
