(ns tictactoe.human-turn
  (:require [tictactoe.rules :refer [valid-spot?]]))

(defn get-move [board & args]
  (loop [input (read-string (read-line))]
    (if (valid-spot? board input)
      input
      (recur (read-string (read-line))))))
