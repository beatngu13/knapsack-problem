package com.github.beatngu13.knapsackproblem.mo.ga;

import com.github.beatngu13.knapsackproblem.base.Item;
import com.github.beatngu13.knapsackproblem.base.Knapsack;
import com.github.beatngu13.knapsackproblem.base.KnapsackFactory;
import com.github.beatngu13.knapsackproblem.mo.MultiObjectiveProblem;
import io.jenetics.Alterer;
import io.jenetics.AltererResult;
import io.jenetics.Genotype;
import io.jenetics.Phenotype;
import io.jenetics.ext.moea.Vec;
import io.jenetics.util.ISeq;
import io.jenetics.util.RandomRegistry;
import io.jenetics.util.Seq;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mutates two knapsacks by adding unused items.
 */
public class UnusedItemsMutator implements Alterer<ItemGene, Vec<int[]>> {

	/**
	 * The altering probability in the range of [0, 1].
	 */
	private final double probability;

	/**
	 * @param The altering probability in the range of [0, 1].
	 */
	public UnusedItemsMutator(final double probability) {
		this.probability = probability;
	}

	@Override
	public AltererResult<ItemGene, Vec<int[]>> alter(final Seq<Phenotype<ItemGene, Vec<int[]>>> population,
													 final long generation) {
		final var random = RandomRegistry.random();
		return population.stream()
				.map(individual -> random.nextInt(100) <= probability * 100
						? addUnusedItems(individual, generation)
						: individual)
				.collect(Collectors.collectingAndThen(ISeq.toISeq(), AltererResult::of));
	}

	private Phenotype<ItemGene, Vec<int[]>> addUnusedItems(final Phenotype<ItemGene, Vec<int[]>> individual,
														   final long generation) {
		final var knapsack0 = ((KnapsackChromosome) individual.genotype().get(0)).knapsack();
		final var knapsack1 = ((KnapsackChromosome) individual.genotype().get(1)).knapsack();

		final var newItems0 = addUnusedItems(knapsack0.items(), knapsack1.items(), knapsack0);
		final var newItems1 = addUnusedItems(newItems0, knapsack1.items(), knapsack1);

		final var genotype = Genotype.of(new KnapsackChromosome(KnapsackFactory.createMO0(newItems0)),
				new KnapsackChromosome(KnapsackFactory.createMO1(newItems1)));

		return Phenotype.of(genotype, generation);
	}

	private Set<Item> addUnusedItems(final Set<Item> itemsFromKnapsack0, final Set<Item> itemsFromKnapsack1,
									 final Knapsack knapsack) {
		final var newItems = new HashSet<>(knapsack.items());

		MultiObjectiveProblem.ITEMS.stream()
				.filter(item -> !itemsFromKnapsack0.contains(item)) // Filter items from first knapsack.
				.filter(item -> !itemsFromKnapsack1.contains(item)) // Filter items from second knapsack.
				.sorted(Comparator.comparing(Item::profit).reversed()) // Sort by highest profit.
				.forEach(unusedItem -> {
					final var newKnapsack = KnapsackFactory.create(newItems, knapsack.maxCapacity());
					final var availableWeight = knapsack.maxCapacity() - newKnapsack.weight();
					final var unusedItemWeight = unusedItem.weight();
					if (availableWeight - unusedItemWeight >= 0) {
						newItems.add(unusedItem);
					}
				});

		return newItems;
	}

}
