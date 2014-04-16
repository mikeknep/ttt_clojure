(ns tictactoe.unbeatable-ai
  (:require [tictactoe.rules :refer [all-spots-taken? winner-on-board?]]))

(defn simple-board-score [board]
  (if (winner-on-board? board)
    1.0
    0.0))

(defn score-board-with-depth [board depth]
  (if (zero? (simple-board-score board))
    0
    (* (/ (simple-board-score board) (double depth)) (reduce * (repeat depth -1)))))

(defn update-best-score [this-score best-score]
  (if (> this-score best-score)
    this-score
    best-score))

(defn update-best-spot [this-spot this-score best-spot best-score]
  (if (> this-score best-score)
    this-spot
    best-spot))
