package com.github.beatngu13.knapsackproblem.mo;

import java.util.List;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.util.ProblemUtil;

import lombok.experimental.UtilityClass;

// Knapsack problem based on P06 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html.
@UtilityClass
public class MultiObjectiveProblem {

	public static final int MAX_CAPACITY_0 = 103;
	public static final int MAX_CAPACITY_1 = 156;
	public static final int NUMBER_OF_KNAPSACKS = 2;

	private static final List<Integer> profits = List.of( //
			78, 35, 89, 36, 94, //
			75, 74, 79, 80, 16);

	private static final List<Integer> weights = List.of( //
			18, 9, 23, 20, 59, //
			61, 70, 75, 76, 30);

	public static final List<Item> ITEMS = ProblemUtil.getItems(profits, weights);

	private static final String optimalSolution_0 = "1010010000";
	private static final String optimalSolution_1 = "0001100010";

	public static final Knapsack OPTIMAL_KNAPSACK_0 = ProblemUtil.getOptimalKnapsack(optimalSolution_0, ITEMS,
			MAX_CAPACITY_0);
	public static final Knapsack OPTIMAL_KNAPSACK_1 = ProblemUtil.getOptimalKnapsack(optimalSolution_1, ITEMS,
			MAX_CAPACITY_1);

}
