;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Advent of Clojure Code 2019
;;
;; Author: @practicalli
;;
;; Day 1: The Tyranny of the Rocket Equation ---
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


(defn -main
  "Run me and I will tell you the answer to the problem, eventually"
  [& args]
  (solve-part-1 data/puzzle-input))

(-main)
;; => 3372463

(defn fuel-for-module
  "Calculate the fuel for a given module based on its mass.

  Divide mass by 3, round down to Integer then subtract 2

  Argument: mass - an integer
  Return: fuel-required - as an integer"
  [module-mass]
  #_(/ module-mass 3) ;; we could divide then round down, but quot is simpler
  (- (quot module-mass 3) 2))


(defn solve-part-1
  "Calculate the fuel required for each module,
  and return the total amount of fuel required
  for all modules"
  [puzzle-input]
  (reduce + (map fuel-for-module puzzle-input)))

#_(solve-part-1 puzzle-input)
;; => 3372463


