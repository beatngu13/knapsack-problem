package com.github.beatngu13.knapsackproblem.base;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnapsackTest {

	@Test
	void should_reject_null_items() {
		assertThatThrownBy(() -> new Knapsack(null, 0)).isInstanceOf(NullPointerException.class)
				.hasMessage("Items must not be null.");
	}

	@Test
	void should_reject_negative_max_capacity() {
		assertThatThrownBy(() -> new Knapsack(new Items(), -1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Max capacity must not be negative.");
	}

}
