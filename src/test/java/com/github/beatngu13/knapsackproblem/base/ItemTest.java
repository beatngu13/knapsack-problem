package com.github.beatngu13.knapsackproblem.base;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ItemTest {

	@Test
	void should_reject_negative_profit() {
		assertThatThrownBy(() -> new Item(-1, 0)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Profit must not be negative.");
	}

	@Test
	void should_reject_negative_weight() {
		assertThatThrownBy(() -> new Item(0, -1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Weight must not be negative.");
	}

}
