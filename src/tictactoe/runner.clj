(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over?]]
            [tictactoe.console-io :refer [get-board-length get-player-token get-player-decision-maker play-again?]]
            [tictactoe.console-presenter :refer [present-board present-current-player present-result]]
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
      (present-board board)
      (if (game-over? board)
        (do (present-result board)
            (if (play-again?) (play (create-board (get-board-length)) (create-player (get-player-decision-maker) (get-player-token)) (create-player (get-player-decision-maker) (get-player-token)))))
        (do (present-current-player current-token)
            (recur (take-turn board (next-turn-fn board current-token opponent-token) current-token)
                   opponent
                   current-player))))))

(defn -main []
  (play (create-board (get-board-length)) (create-player (get-player-decision-maker) (get-player-token)) (create-player (get-player-decision-maker) (get-player-token))))
