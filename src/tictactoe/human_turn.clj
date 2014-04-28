(ns tictactoe.human-turn
  (:require [tictactoe.rules :refer [valid-spot?]]
            [tictactoe.console-presenter :refer [format-board]]))


(defn get-move [board & args]
  (loop [prompt "Where do you want to go next?"]
    (println prompt)
    (println (format-board board :legend))
    (let [input (read-line)]
      (if (valid-spot? board (read-string input))
        (read-string input)
        (recur "That is not a valid spot. Please input the index of the spot you want to play next as an integer.")))))
