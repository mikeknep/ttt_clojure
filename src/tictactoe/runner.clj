(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over? current-token opponent-token]]
            [tictactoe.console-io :refer [display-board display-spot declare-whose-turn declare-result]]
            [tictactoe.gameplay :refer [take-turn]]))


(defn play [spots player-x player-o]
  (loop [spots spots]
    (let [current-token   (current-token spots)
          opponent-token  (opponent-token spots)
          current-player  (if (= :x current-token) player-x player-o)
          next-turn-fn    (get current-player :decision-maker)]
      (display-board spots)
      (if (game-over? spots)
        (declare-result spots)
        (do (declare-whose-turn (display-spot current-token))
            (recur (take-turn spots (next-turn-fn spots current-token opponent-token) current-token)))))))
