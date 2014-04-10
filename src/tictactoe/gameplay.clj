(ns tictactoe.gameplay
  (:require [tictactoe.rules :refer [valid-spot?]]))

(defn take-turn [spots index token]
  (if (valid-spot? spots index)
    (assoc spots index token)
    spots))
