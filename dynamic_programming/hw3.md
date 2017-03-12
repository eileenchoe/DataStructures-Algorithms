CMSI 282 Programming Assignment

SumoSolver

Sumo wrestler Keiryō needs to put on weight in a hurry. With money in hand, he heads to a specialty food store where he is confronted with many items, each of which has a cost (in dollars) and a guarantee of the weight (in pounds) that he'll gain if he eats that item. His dilemma is which items to buy so as to maximize his total weight gain. Your Java program, SumoSolver, will help him solve arbitrary instances of this problem via Dynamic Programming.

The command-line args will be a sequence of cost-weight pairs, followed by the maximum amount he can spend. For example, java SumoSolver 48 51 49 52 55 99 100 describes an instance in which there are three items: the first item costs $48 and guarantees a weight gain of 51 pounds; the second item costs $49 and guarantees a gain of 52 pounds; the third item costs $55 and guarantees a gain of 99 pounds; and Keiryō can spend at most $100.

Keiryō may take at most one of each item, and he must take all or none of that item.

All args should be positive integers, but, of course, your program should check their validity.

The output must look like this:

$48 / 51 pounds

$49 / 52 pounds

2 items / $97 / 103 pounds

- Use dynamic programming with recursion and memoization
