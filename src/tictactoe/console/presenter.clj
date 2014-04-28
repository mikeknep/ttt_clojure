(ns tictactoe.console.presenter
  (:require [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [winner-present? get-winner]]))

(defn present-current-player [token]
  (str token "'s turn"))

(defn present-draw []
  "Cat's game!")

(defn present-winner [token]
  (str token " wins!"))

(defn present-result [board]
  (if (winner-present? board)
    (present-winner (get-winner board))
    (present-draw)))
