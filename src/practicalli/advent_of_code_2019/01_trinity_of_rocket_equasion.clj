;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Advent of Clojure Code 2019
;;
;; Author: @practicalli
;;
;; Day 1: The Tyranny of the Rocket Equation
;; Parts 1 & 2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(ns practicalli.advent-of-code-2019.01-trinity-of-rocket-equasion
  (:gen-class)
  (:require [practicalli.advent-of-code-2019.01-trinity-of-rocket-equasion-data :as data]))

;; Part 1 - calculating total fuel
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Santa has become stranded at the edge of the Solar System while delivering presents to other planets! To accurately calculate his position in space, safely align his warp drive, and return to Earth in time to save Christmas, he needs you to bring him measurements from fifty stars.

;; Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

;; The Elves quickly load you into a spacecraft and prepare to launch.

;; At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper. They haven't determined the amount of fuel required yet.

;; Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.

;; For example:

;; For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
;; For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
;; For a mass of 1969, the fuel required is 654.
;; For a mass of 100756, the fuel required is 33583.


;; The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.

;; What is the sum of the fuel requirements for all of the modules on your spacecraft?

;; To begin, get your puzzle input.


(defn fuel-for-module
  "Calculate the fuel for a given module based on its mass.

  Divide mass by 3, round down to Integer then subtract 2

  Argument: mass - an integer
  Return: fuel-required - as an integer"
  [module-mass]
  #_(/ module-mass 3) ;; we could divide then round down, but quot is simpler
  (- (quot module-mass 3) 2))

#_(fuel-for-module 14)
#_(fuel-for-module 100756)

(defn solve-part-1
  "Calculate the fuel required for each module,
  and return the total amount of fuel required
  for all modules"
  [puzzle-input]
  (reduce + (map fuel-for-module puzzle-input)))

(map fuel-for-module data/puzzle-input)


(solve-part-1 data/puzzle-input)


(defn -main
  "Run me and I will tell you the answer to the problem, eventually"
  [& args]
  (solve-part-1 data/puzzle-input))

#_(-main)




;; Part Two
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; During the second Go / No Go poll, the Elf in charge of the Rocket Equation Double-Checker stops the launch sequence. Apparently, you forgot to include additional fuel for the fuel you just added.

;; Fuel itself requires fuel just like a module - take its mass, divide by three, round down, and subtract 2. However, that fuel also requires fuel, and that fuel requires fuel, and so on. Any mass that would require negative fuel should instead be treated as if it requires zero fuel; the remaining mass, if any, is instead handled by wishing really hard, which has no mass and is outside the scope of this calculation.

;; So, for each module mass, calculate its fuel and add it to the total. Then, treat the fuel amount you just calculated as the input mass and repeat the process, continuing until a fuel requirement is zero or negative. For example:

;; A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0, which would call for a negative fuel), so the total fuel required is still just 2.
;; At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216 then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel. So, the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
;; The fuel required by a module of mass 100756 and its fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.

;; What is the sum of the fuel requirements for all of the modules on your spacecraft when also taking into account the mass of the added fuel? (Calculate the fuel requirements for each module separately, then add them all up at the end.)


;; Visualising the results with Quil
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; http://www.quil.info/sketches/local/0622589e4acd61f73d86eff09064deff308553905d58dfc10448cf244ce9fa24


;; Taking a low level loop recur approach
;; find the fuel for the mass,
;; then find the fuel for the fuel
;; keep going until no more fuel is needed for the fuel

(defn total-fuel-for-module
  "Calculate the fuel for a given module based on its mass.  Then calculate the fuel for the fuel.

  `quot` is used to divide mass by 3 and return a whole number, then subtract 2 to get the amount of fuel required

  Argument: mass - an integer
  Return: total - total fuel-required as an integer"

  [module-mass]
  (loop [mass  module-mass
         fuel  (- (quot mass 3) 2)
         total 0]
    (if (<= fuel 5)
      (+ total fuel)
      (recur fuel
             (- (quot fuel 3) 2)
             (+ total fuel)))))


;; Two quick tests

(total-fuel-for-module 14)

(total-fuel-for-module 1969)


;; The total fuel for all the modules

(reduce +
        (map total-fuel-for-module data/puzzle-input))




;; Using transducers to solve the challenge
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; What are tranducers
;; A general pupose way of applying algorithmic transformations.
;; They are a recipe on how to transform data,
;; without knowing what that data is.

;; Some of the `clojure.core` functions act as a transducer

(map total-fuel-for-module ,,,,)

;; Previously we covered reduce and how it iterates
;; a function over a collection of values

;; That function can be simple:
(reduce * [1 2 3 4 5])

;; Or we can write our own function

(reduce custom-algorithm (range 1000))

;; Custom functions can become quite involved
;; especially with nested expressions
;; but its the same principle.
(reduce +
        (filter odd?
                (map #(+ 7 %)
                     (range 100))))

;; A reducing function is just the way of referring
;; to a function passed to `reduce`
;; In the above example, the whole expression starting
;; with filter is the reducing function


;; We could abstract that algorithm and make it
;; easier to use elsewhere.

(def algorithm
  (comp (map #(+ 7 %) ,,,)
        (filter odd?)
        ,,,,
        ,,,))

;; Notice that (filter odd?) and `(map #(+ 7 %))`
;; to not take any values as arguments, they are missing
;; `map` and `filter` return a transducer when only
;; given a single function as an argument (eg. no value)

;; Now the `transduce` function can be used
;; with our `algorithm` to transform data

(transduce algorithm + (range 100))
;; => 2800

;; We get the same results as the reduce
;; however our code is more adaptable

;; An initial value can also be added
(transduce algorithm + -1000 (range 100))


;; Define a name for our transformation
;; comp sends the results of the first argument, `map`
;; to the second argument, `take`

(def add-values
  (comp (map #(+ % 7))
        (take 12)))

;; The transformation is then applied over the
;; collection of values
;; the results are reduced using the `*` function

(transduce add-values * [1 2 3 4 5])


;; Back to the Advent of code challenge
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; We have our simple function to calculate
;; the fuel required for a given mass

(defn fuel-required
  [mass]
  (- (quot mass 3) 2))


;; We can see the the individual fuel values for each mass
(map fuel-required data/puzzle-input)

;; and get the total

(reduce +
        (map fuel-required data/puzzle-input))

;; We could convert this into a transformation
;; passing just the `fuel-required` function to `map`
;; which then returns a transducer

(def xform
  (comp
    (map fuel-required)))

;; Then use transduce to apply the transformation
;; and `+` as the final reducing function
;; to get the total fuel required
(transduce xform + data/puzzle-input)

;; As its simple, we could just write it out in one expression
(transduce (map fuel-required) + data/puzzle-input)



;; Using transduce for part 2
;; using our existing fuel calculation function

(transduce (map total-fuel-for-module)
           +
           data/puzzle-input)


;; Using an iterate approach, we get the same results

(defn fuel-delta [mass]
  (->> (iterate fuel-required mass)
       (rest)
       (take-while pos?)
       (reduce +)))

(transduce (map fuel-delta) + data/puzzle-input)
;; => 5055835



(defn fuel*
  [mass]
  (->> (iterate fuel mass)
       (drop 1)
       (take-while pos?)
       (apply +)))


;; Final thoughts
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; The advantage of using transduce over reduce?
;; - speed & memory
;; - fewer allocations for intermediate structures
