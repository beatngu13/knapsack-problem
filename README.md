# knapsack-problem

A solution for the [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem) with the aid of a [genetic algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm). The implementation uses the excellent [Jenetics](http://jenetics.io/) library for Java.

Whereas most examples around Jenetics are very concise, the goal of this project is to present a detailed implementation for beginners that demonstrates Jenetic's capabilities.

## The Problem

The knapsack problem we have chosen is by [John Burkardt](https://people.sc.fsu.edu/~jburkardt/) from the [Department of Scientific Computing of the Florida State University](https://sc.fsu.edu/), see P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html.

## TODOs

- [ ] Use `Gene` interface instead of `AnyGene`
- [ ] Add custom `Alterer`s.
- [ ] Switch to a multi-objective problem
- [ ] Improve documentation (e.g. Javadoc)
