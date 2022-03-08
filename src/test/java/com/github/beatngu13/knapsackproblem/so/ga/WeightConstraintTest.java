package com.github.beatngu13.knapsackproblem.so.ga;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Items;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.engine.Constraint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeightConstraintTest {

	Constraint<ItemGene, Integer> cut = new WeightConstraint();

	@Test
	void should_accept_individuals_within_max_capacity() {
		final var items = new Items(new Item(0, 0));
		final var knapsack = new Knapsack(items, 0);
		final var individual = toPhenotype(knapsack);

		final var valid = cut.test(individual);

		assertThat(valid).isTrue();
	}

	@Test
	void should_reject_individuals_beyond_max_capacity() {
		final var items = new Items(new Item(0, 100));
		final var knapsack = new Knapsack(items, 0);
		final var individual = toPhenotype(knapsack);

		final var valid = cut.test(individual);

		assertThat(valid).isFalse();
	}

	@Test
	void should_create_new_instance_for_repair() {
		final var items = new Items();
		final var knapsack = new Knapsack(items, 0);
		final var individual = toPhenotype(knapsack);

		final var repairedIndividual = cut.repair(individual, individual.generation());

		assertThat(repairedIndividual.generation()).isZero();
	}

	private static Phenotype<ItemGene, Integer> toPhenotype(final Knapsack knapsack) {
		return Phenotype.of(Genotype.of(new KnapsackChromosome(knapsack)), 1L);
	}

}
