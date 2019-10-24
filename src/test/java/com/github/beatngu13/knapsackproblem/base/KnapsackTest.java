package com.github.beatngu13.knapsackproblem.base;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;

class KnapsackTest {

	@Test
	void sets_should_be_mutually_exclusive() {
		final var listOfSets = Knapsack.generateKnapsacks();
		final var set0 = listOfSets.get(0);
		final var set1 = listOfSets.get(1);
		assertThat(Collections.disjoint(set0, set1)).isTrue();
	}

	@Test
	void sets_should_satisfy_max_capacity_condition() {
		final var listOfSets = Knapsack.generateKnapsacks();
		final int[] maxCapacities = new int[] { MultiObjectiveProblem.MAX_CAPACITY_0,
				MultiObjectiveProblem.MAX_CAPACITY_1 };
		final boolean isConditionSatisfied = IntStream.range(0, listOfSets.size()) //
				.allMatch(i -> listOfSets.get(i).stream() //
						.map(item -> item.getWeight()) //
						.mapToInt(Integer::intValue) //
						.sum() <= maxCapacities[i]);
		assertThat(isConditionSatisfied).isTrue();
	}

}
