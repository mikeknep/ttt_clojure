(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over? current-token opponent-token]]
            [tictactoe.console-io :refer [display-board display-spot declare-whose-turn declare-result]]
            [tictactoe.gameplay :refer [take-turn]]))


(defn play [board player-x player-o]
  (loop [board board]
    (let [current-token   (current-token board)
          opponent-token  (opponent-token board)
          current-player  (if (= :x current-token) player-x player-o)
          next-turn-fn    (get current-player :decision-maker)]
      (display-board board)
      (if (game-over? board)
        (declare-result board)
        (do (declare-whose-turn (display-spot current-token))
            (recur (take-turn board (next-turn-fn board current-token opponent-token) current-token)))))))
