![.github/workflows/maven.yml](https://github.com/beatngu13/knapsack-problem/workflows/.github/workflows/maven.yml/badge.svg)

# knapsack-problem

[Jenetics](http://jenetics.io/) is an awesome Java library for [genetic algorithms (GAs)](https://en.wikipedia.org/wiki/Genetic_algorithm). Unfortunately, many examples for Jenetics are very concise, which makes it hard for beginners to use them as a starting point for their custom problems.

The goal of this project is to provide a detailed implementation that solves the infamous [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem), extending various base classes to illustrate Jenetics' core concepts.

## The Problem(s)

The project consists of _two_ implementations: a single- (`so` package) and a [multi-objective optimization](https://en.wikipedia.org/wiki/Multi-objective_optimization) (`mo` package). Both knapsack problems stem from [John Burkardt's home page](https://people.sc.fsu.edu/~jburkardt/) from the [Department of Scientific Computing of the Florida State University](https://sc.fsu.edu/):

* Single-objective: see P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html, implemented within the integration test `SingleObjectiveIT`.
* Multi-objective: see P06 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html, implemented within the integration test `MultiObjectiveIT`.

Note that while the knapsack problem is a good example to demonstrate a single-objective GA, it is generally not necessary to use a multi-objective GA for multiple knapsacks. The different objectives—i.e. the separate knapsacks—do not compete with each other. One is basically only interested in the combination of knapsacks that yields the highest profit. However, the example still helps to get used to Jenetics' relatively new API for multi-objective problems (`io.jenetics.ext.moea`).

## TODOs

- [x] Use `Gene` interface instead of `AnyGene`
- [x] Add custom `Alterer`
- [x] Add multi-objective problem
- [x] Set up Travis build
- [x] Set up SonarCloud
- [ ] Improve documentation (e.g. Javadoc)
- [ ] Increase test/code coverage
