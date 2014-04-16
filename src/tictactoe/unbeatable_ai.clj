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
