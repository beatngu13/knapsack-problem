package com.github.beatngu13.knapsackproblem.base;

/**
 * A knapsack item, consisting of a unique ID, a profit and a weight.
 *
 * @param id     A knapsack problem might have multiple items that share the
 *               same weight and profit, the ID ensures that items can still be
 *               uniquely identified.
 * @param profit The profit of the item.
 * @param weight The weight of the item.
 */
public record Item(int id, int profit, int weight) {

}
