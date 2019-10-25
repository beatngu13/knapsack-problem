package com.github.beatngu13.knapsackproblem.base;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;

class KnapsackTest {

	@Test
	void items_should_be_mutually_exclusive() {
		final var knapsacks = Knapsack.newInstances();
		final var items0 = knapsacks.get(0).getItems();
		final var items1 = knapsacks.get(1).getItems();
		assertThat(Collections.disjoint(items0, items1)).isTrue();
	}

	@Test
	void sets_should_satisfy_max_capacity_condition() {
		final var knapsacks = Knapsack.newInstances();
		final int[] maxCapacities = new int[] { MultiObjectiveProblem.MAX_CAPACITY_0,
				MultiObjectiveProblem.MAX_CAPACITY_1 };
		final boolean isConditionSatisfied = IntStream.range(0, knapsacks.size()) //
				.allMatch(i -> knapsacks.get(i).getItems().stream() //
						.map(item -> item.getWeight()) //
						.mapToInt(Integer::intValue) //
						.sum() <= maxCapacities[i]);
		assertThat(isConditionSatisfied).isTrue();
	}

}
