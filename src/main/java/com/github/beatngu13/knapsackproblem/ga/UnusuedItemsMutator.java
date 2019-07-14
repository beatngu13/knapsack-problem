package com.github.beatngu13.knapsackproblem.ga;

import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.github.beatngu13.knapsackproblem.Problem;
import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;

import io.jenetics.Alterer;
import io.jenetics.AltererResult;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.util.ISeq;
import io.jenetics.util.RandomRegistry;
import io.jenetics.util.Seq;

/**
 * Mutates a knapsack by adding unused items.
 */
public class UnusuedItemsMutator implements Alterer<ItemGene, Integer> {

	/**
	 * The altering probability.
	 */
	private final double probability;

	/**
	 * @param The altering probability.
	 */
	public UnusuedItemsMutator(final double probability) {
		this.probability = probability;
	}

	@Override
	public AltererResult<ItemGene, Integer> alter(final Seq<Phenotype<ItemGene, Integer>> population,
			final long generation) {
		final var random = RandomRegistry.getRandom();
		return population.stream() //
				.map(individual -> random.nextInt(100) <= probability * 100 //
						? addUnusedItems(individual, generation) //
						: individual) //
				.collect(Collectors.collectingAndThen(ISeq.toISeq(), AltererResult::of));
	}

	/**
	 * @param individual The individual knapsack to be altered.
	 * @param generation The date of birth (generation) of the altered phenotypes.
	 * @return The knapsack with additional, previously unused items (sorted by
	 *         profit).
	 */
	private Phenotype<ItemGene, Integer> addUnusedItems(final Phenotype<ItemGene, Integer> individual,
			final long generation) {
		final var knapsack = ((KnapsackChromosome) individual.getGenotype().getChromosome()).getKnapsack();
		final var newItems = new HashSet<Item>(knapsack.getItems());

		Problem.ITEMS.stream() //
				.filter(item -> !knapsack.getItems().contains(item)) // Filter for unused items.
				.sorted(Comparator.comparing(Item::getProfit).reversed()) // Sort by highest profit.
				.forEach(unusedItem -> {
					final var availableWeight = Problem.MAX_CAPACITY - new Knapsack(newItems).getWeight();
					final var itemWeight = unusedItem.getWeight();
					if (availableWeight - itemWeight >= 0) {
						newItems.add(unusedItem);
					}
				});

		return Phenotype.of(Genotype.of(new KnapsackChromosome(new Knapsack(newItems))), generation);
	}

}
