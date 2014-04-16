(ns tictactoe.simple-ai)

(defn choose-random-spot [spots]
  (rand-nth (keep-indexed #(if (nil? %2) %1) spots)))
