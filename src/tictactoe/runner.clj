(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over? current-token opponent-token]]
            [tictactoe.console-io :refer [get-board-length get-player-decision-maker display-board display-spot declare-whose-turn declare-result play-again?]]
            [tictactoe.gameplay :refer [take-turn]]
            [tictactoe.board :refer [create-board]]
            [tictactoe.player :refer [create-player]]))


(defn play [board player-x player-o]
  (loop [board board]
    (let [current-token   (current-token board)
          opponent-token  (opponent-token board)
          current-player  (if (= :x current-token) player-x player-o)
          next-turn-fn    (get current-player :decision-maker)]
      (display-board board)
      (if (game-over? board)
        (do (declare-result board)
            (if (play-again?) (play (create-board (get-board-length)) (create-player :x (get-player-decision-maker)) (create-player :o (get-player-decision-maker)))))
        (do (declare-whose-turn (display-spot current-token))
            (recur (take-turn board (next-turn-fn board current-token opponent-token) current-token)))))))

(defn -main []
  (play (create-board (get-board-length)) (create-player :x (get-player-decision-maker)) (create-player :o (get-player-decision-maker))))
