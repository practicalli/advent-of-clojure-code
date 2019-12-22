# Advent-of-code-2019

Solving the [Advent of Code challenges from 2019](https://adventofcode.com/2019).

Taking examples from across the community and comparing them.

## Installation

Clone the project with your favourite git client, eg. Magit.

https://github.com/practicalli/advent-of-code-2019


## Solving the challenges

Open the project in your favourite editor or REPL and run the `-main` function in each namespace to generate the result.

    $ clj -m practicalli.advent-of-code-2019.01-trinity-of-rocket-equasion

The puzzle data is in a sibling namespace, postfixed with the name `-data.clj`.  Simply replace your own data in the `(def data)` expression

You can also just explore the different functions and approaches by evaluating the code.


## Testing the code

Run the unit tests (if we write any) using the Cognitect test runner (automatically installs itself on first run):

    $ clj -A:test:runner

## License

Copyright © 2019 Practicalli

Distributed under the Creative Commons Attribution Share-Alike 4.0 International
