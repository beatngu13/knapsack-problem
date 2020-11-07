package com.github.beatngu13.knapsackproblem.mo;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.util.ProblemUtil;

import java.util.Collections;
import java.util.List;

/**
 * Multi-objective knapsack problem based on P06 from <a href=
 * "https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html">https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html</a>.
 */
public class MultiObjectiveProblem {

	private MultiObjectiveProblem() {

	}

	private static final List<Integer> PROFITS = List.of(
			78, 35, 89, 36, 94,
			75, 74, 79, 80, 16);

	private static final List<Integer> WEIGHTS = List.of(
			18, 9, 23, 20, 59,
			61, 70, 75, 76, 30);

	private static final String OPTIMAL_SOLUTION_0 = "1010010000";
	private static final String OPTIMAL_SOLUTION_1 = "0001100010";

	public static final List<Item> ITEMS = Collections.unmodifiableList(ProblemUtil.getItems(PROFITS, WEIGHTS));

	public static final int MAX_CAPACITY_0 = 103;
	public static final int MAX_CAPACITY_1 = 156;

	public static final Knapsack OPTIMAL_KNAPSACK_0 = ProblemUtil.getOptimalKnapsack(OPTIMAL_SOLUTION_0, ITEMS,
			MAX_CAPACITY_0);
	public static final Knapsack OPTIMAL_KNAPSACK_1 = ProblemUtil.getOptimalKnapsack(OPTIMAL_SOLUTION_1, ITEMS,
			MAX_CAPACITY_1);

	public static final int NUMBER_OF_KNAPSACKS = 2;

}
