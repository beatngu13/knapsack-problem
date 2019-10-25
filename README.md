# knapsack-problem

A solution for the [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem) with the aid of a [genetic algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm). The implementation uses the excellent [Jenetics](http://jenetics.io/) library for Java.

Whereas most examples around Jenetics are very concise, the goal of this project is to present a detailed implementation for beginners, which explains various core classes and concepts of Jenetics.

## The Problem

Both knapsack problems stem from [John Burkardt's home page](https://people.sc.fsu.edu/~jburkardt/) from the [Department of Scientific Computing of the Florida State University](https://sc.fsu.edu/).

While the knapsack problem is a good example to demonstrate single-objective GAs, it should be noted that it is generally not necessary to use [multi-objective optimization](https://en.wikipedia.org/wiki/Multi-objective_optimization) for multiple knapsacks. The different objectives—i.e. the separate knapsacks—do not compete with each other, we are basically only interested in the composition of knapsacks which yields the highest profit. However, the example still helps to get used to Jenetics' relatively new API for multi-objective problems.

### Single-Objective

See P07 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html.

### Multi-Objective

See P06 from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_multiple/knapsack_multiple.html.

## TODOs

- [x] Use `Gene` interface instead of `AnyGene`
- [x] Add custom `Alterer`
- [x] Add multi-objective problem
- [ ] Increase test/code coverage
- [ ] Set up Travis build
- [ ] Set up SonarCloud
- [ ] Improve documentation (e.g. Javadoc)
