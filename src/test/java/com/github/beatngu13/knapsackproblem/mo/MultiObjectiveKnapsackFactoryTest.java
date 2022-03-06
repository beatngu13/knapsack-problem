package com.github.beatngu13.knapsackproblem.mo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultiObjectiveKnapsackFactoryTest {

	@Test
	void items_should_be_mutually_exclusive() {
		final var knapsacks = MultiObjectiveKnapsackFactory.createRandom();
		final var items0 = knapsacks.get(0).items();
		final var items1 = knapsacks.get(1).items();
		assertThat(items0).doesNotContainAnyElementsOf(items1);
	}

	@Test
	void items_should_satisfy_max_capacity_condition() {
		final var knapsacks = MultiObjectiveKnapsackFactory.createRandom();
		final var knapsack0 = knapsacks.get(0);
		final var knapsack1 = knapsacks.get(1);
		assertThat(knapsack0.maxCapacity()).isEqualTo(MultiObjectiveProblem.MAX_CAPACITY_0);
		assertThat(knapsack0.weight()).isLessThanOrEqualTo(knapsack0.maxCapacity());
		assertThat(knapsack1.maxCapacity()).isEqualTo(MultiObjectiveProblem.MAX_CAPACITY_1);
		assertThat(knapsack1.weight()).isLessThanOrEqualTo(knapsack1.maxCapacity());
	}

}