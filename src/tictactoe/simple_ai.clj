(ns tictactoe.simple-ai)

(defn choose-random-spot [spots & args]
  (rand-nth (keep-indexed #(if (nil? %2) %1) spots)))
