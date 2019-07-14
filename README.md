# knapsack-problem

A solution for the [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem) with the aid of a [genetic algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm). The implementation uses the excellent [Jenetics](http://jenetics.io/) library for Java.

Whereas most examples around Jenetics are very concise, the goal of this project is to present a detailed implementation for beginners, which explains various core classes and concepts of Jenetics.

## The Problem

The chosen knapsack problem is by [John Burkardt](https://people.sc.fsu.edu/~jburkardt/) from the [Department of Scientific Computing of the Florida State University](https://sc.fsu.edu/), see P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html.

## TODOs

- [x] Use `Gene` interface instead of `AnyGene`
- [x] Add custom `Alterer`
- [ ] Increase test/code coverage
- [ ] Set up Travis build
- [ ] Set up SonarCloud
- [ ] Switch to a multi-objective problem
- [ ] Improve documentation (e.g. Javadoc)
