(ns tictactoe.unbeatable-ai
  (:require [tictactoe.rules :refer [all-spots-taken? winner-on-board?]]))

(defn score-board [board depth]
  (if (winner-on-board? board)
    (double (reduce * (repeat depth -1.0)))
    0))
