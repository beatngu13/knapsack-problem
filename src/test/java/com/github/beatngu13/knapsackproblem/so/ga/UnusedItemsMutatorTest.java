package com.github.beatngu13.knapsackproblem.so.ga;

import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.so.SingleObjectiveKnapsackFactory;
import com.github.beatngu13.knapsackproblem.so.SingleObjectiveProblem;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.util.Seq;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UnusedItemsMutatorTest {

	@Test
	void should_not_mutate_when_probability_is_zero() {
		final var knapsack = SingleObjectiveKnapsackFactory.create(new Items());
		final var cut = new UnusedItemsMutator(0.0);

		final var result = cut.alter(toPhenotypeSeq(knapsack), 1L);

		final var knapsacks = toKnapsackList(result.population());
		assertThat(knapsacks).size().isOne();
		assertThat(knapsacks).first().isEqualTo(knapsack);
	}

	@Test
	void should_not_mutate_knapsack_without_available_weight() {
		final var cut = new UnusedItemsMutator(1.0);

		final var result = cut.alter(toPhenotypeSeq(SingleObjectiveProblem.OPTIMAL_KNAPSACK), 1L);

		final var knapsacks = toKnapsackList(result.population());
		assertThat(knapsacks).size().isOne();
		assertThat(knapsacks).first().isEqualTo(SingleObjectiveProblem.OPTIMAL_KNAPSACK);
	}

	@Test
	void should_mutate_knapsack_with_available_weight() {
		final var knapsack = SingleObjectiveKnapsackFactory.create(new Items());
		final var cut = new UnusedItemsMutator(1.0);

		final var result = cut.alter(toPhenotypeSeq(knapsack), 1L);

		final var knapsacks = toKnapsackList(result.population());
		// Previously unused items, sorted by profit.
		final var expected = SingleObjectiveKnapsackFactory.create(
				new Items(SingleObjectiveProblem.ITEMS.subList(9, SingleObjectiveProblem.ITEMS.size())));
		assertThat(knapsacks).size().isOne();
		assertThat(knapsacks).first().isEqualTo(expected);
	}

	private static Seq<Phenotype<ItemGene, Integer>> toPhenotypeSeq(final Knapsack... knapsacks) {
		return Arrays.stream(knapsacks)
				.map(UnusedItemsMutatorTest::toPhenotype)
				.collect(Seq.toSeq());
	}

	private static Phenotype<ItemGene, Integer> toPhenotype(final Knapsack knapsack) {
		return Phenotype.of(Genotype.of(new KnapsackChromosome(knapsack)), 1L);
	}

	private static List<Knapsack> toKnapsackList(final Seq<Phenotype<ItemGene, Integer>> population) {
		return population.map(UnusedItemsMutatorTest::toKnapsack).asList();
	}

	private static Knapsack toKnapsack(final Phenotype<ItemGene, Integer> individual) {
		return individual.genotype()
				.chromosome()
				.as(KnapsackChromosome.class)
				.knapsack();
	}

}
