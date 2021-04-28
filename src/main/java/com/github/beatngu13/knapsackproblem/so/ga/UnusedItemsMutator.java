package com.github.beatngu13.knapsackproblem.so.ga;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;
import com.github.beatngu13.knapsackproblem.so.SingleObjectiveProblem;
import com.github.beatngu13.knapsackproblem.util.Items;
import io.jenetics.Alterer;
import io.jenetics.AltererResult;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.util.ISeq;
import io.jenetics.util.RandomRegistry;
import io.jenetics.util.Seq;

import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Mutates a knapsack by adding unused items.
 */
public class UnusedItemsMutator implements Alterer<ItemGene, Integer> {

	/**
	 * The altering probability in the range of [0, 1].
	 */
	private final double probability;

	/**
	 * @param probability The altering probability in the range of [0, 1].
	 */
	public UnusedItemsMutator(final double probability) {
		this.probability = probability;
	}

	@Override
	public AltererResult<ItemGene, Integer> alter(final Seq<Phenotype<ItemGene, Integer>> population,
												  final long generation) {
		final var random = RandomRegistry.random();
		return population.stream()
				.map(individual -> random.nextInt(100) <= probability * 100
						? addUnusedItems(individual, generation)
						: individual)
				.collect(Collectors.collectingAndThen(ISeq.toISeq(), AltererResult::of));
	}

	/**
	 * @param individual The individual knapsack to be altered.
	 * @param generation The date of birth (generation) of the altered phenotypes.
	 * @return The knapsack with additional, previously unused items (sorted by
	 * profit).
	 */
	private Phenotype<ItemGene, Integer> addUnusedItems(final Phenotype<ItemGene, Integer> individual,
														final long generation) {
		final var items = individual.genotype()
				.chromosome()
				.as(KnapsackChromosome.class)
				.knapsack()
				.items();
		final var newItems = Items.set(items);

		SingleObjectiveProblem.ITEMS.stream()
				.filter(not(items::contains)) // Filter for unused items.
				.sorted(Comparator.comparing(Item::profit).reversed()) // Sort by highest profit.
				.forEach(unusedItem -> {
					final var newKnapsack = KnapsackFactory.createSO(newItems);
					final var availableWeight = SingleObjectiveProblem.MAX_CAPACITY - newKnapsack.weight();
					final var unusedItemWeight = unusedItem.weight();
					if (availableWeight - unusedItemWeight >= 0) {
						newItems.add(unusedItem);
					}
				});

		return Phenotype.of(Genotype.of(new KnapsackChromosome(KnapsackFactory.createSO(newItems))), generation);
	}

}
