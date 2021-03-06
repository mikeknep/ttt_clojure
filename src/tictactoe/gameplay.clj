(ns tictactoe.gameplay
  (:require [tictactoe.rules :refer [valid-spot?]]))

(defn take-turn [board index token]
  (if (valid-spot? board index)
    (assoc board index token)
    board))
