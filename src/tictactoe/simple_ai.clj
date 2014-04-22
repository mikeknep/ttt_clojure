(ns tictactoe.simple-ai)

(defn choose-random-spot [board & args]
  (rand-nth (keep-indexed #(if (nil? %2) %1) board)))
