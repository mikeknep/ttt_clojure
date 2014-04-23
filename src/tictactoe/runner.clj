(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over?]]
            [tictactoe.console-io :refer [get-board-length get-player-decision-maker display-board declare-whose-turn declare-result play-again?]]
            [tictactoe.gameplay :refer [take-turn]]
            [tictactoe.board :refer [create-board]]
            [tictactoe.player :refer [create-player]]))


(defn play [board player-1 player-2]
  (loop [board          board
         current-player player-1
         opponent       player-2]
    (let [current-token   (get current-player :token)
          opponent-token  (get opponent :token)
          next-turn-fn    (get current-player :decision-maker)]
      (display-board board)
      (if (game-over? board)
        (do (declare-result board)
            (if (play-again?) (play (create-board (get-board-length)) (create-player :x (get-player-decision-maker)) (create-player :o (get-player-decision-maker)))))
        (do (declare-whose-turn current-token)
            (recur (take-turn board (next-turn-fn board current-token opponent-token) current-token)
                   opponent
                   current-player))))))

(defn -main []
  (play (create-board (get-board-length)) (create-player :x (get-player-decision-maker)) (create-player :o (get-player-decision-maker))))
