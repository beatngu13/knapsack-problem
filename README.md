![.github/workflows/build.yml](https://github.com/beatngu13/knapsack-problem/workflows/.github/workflows/build.yml/badge.svg)

# knapsack-problem

[Jenetics](http://jenetics.io/) is an awesome Java library for [genetic algorithms (GAs)](https://en.wikipedia.org/wiki/Genetic_algorithm). Many Jenetics examples are very concise and make excessive use of lambdas, which can make it hard for beginners to take them as a starting point for their custom problems.

The goal of this project is to provide a detailed implementation that solves the infamous [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem), implementing the following interfaces to illustrate Jenetics' core concepts:

* [`Gene`](https://jenetics.io/javadoc/jenetics/6.0/io/jenetics/Gene.html)
* [`Chromosome`](https://jenetics.io/javadoc/jenetics/6.0/io/jenetics/Chromosome.html)
* [`Codec`](https://jenetics.io/javadoc/jenetics/6.0/io/jenetics/engine/Codec.html)
* (Fitness) [`Function`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Function.html)
* [`Alterer`](https://jenetics.io/javadoc/jenetics/6.0/io/jenetics/Alterer.html)
* [`Constraint`](https://jenetics.io/javadoc/jenetics/6.0/io/jenetics/engine/Constraint.html)

## The Problem(s)

The project consists of _two_ implementations: a single- (`so` package) and a [multi-objective optimization](https://en.wikipedia.org/wiki/Multi-objective_optimization) (`mo` package). Both knapsack problems stem from [John Burkardt's home page](https://people.sc.fsu.edu/~jburkardt/) from the [Department of Scientific Computing of the Florida State University](https://sc.fsu.edu/):

* Single-objective: see P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html, implemented within the integration test `SingleObjectiveIT`.
* Multi-objective: see P06 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html, implemented within the integration test `MultiObjectiveIT`.

Note that while the knapsack problem is a good example to demonstrate a single-objective GA, it is generally not necessary to use a multi-objective GA for multiple knapsacks. The different objectives—i.e. the separate knapsacks—do not compete with each other. One is basically only interested in the combination of knapsacks that yields the highest profit. However, the example still helps to get used to Jenetics' relatively new API for multi-objective problems (`io.jenetics.ext.moea`).
