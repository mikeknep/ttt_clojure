(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over?]]
            [tictactoe.console.io :refer [prompt]]
            [tictactoe.console.text-presenter :refer [present-with-padding present-current-player present-result get-play-again]]
            [tictactoe.player :refer [human?]]
            [tictactoe.console.board-presenter :refer [present-board]]
            [tictactoe.gameplay :refer [take-turn]]
            [tictactoe.setup :refer [setup-new-game]]))

(defn play [new-game]
  (loop [board          (new-game :board)
         current-player (new-game :player-1)
         opponent       (new-game :player-2)]
    (let [current-token   (current-player :token)
          opponent-token  (opponent :token)
          next-turn-fn    (current-player :decision-maker)]
      (println (present-with-padding (present-board board :traditional)))
      (if (game-over? board)
        (do (println (present-result board))
            (if (prompt get-play-again) (play (setup-new-game))))
        (do (println (present-current-player current-token))
            (if (human? current-player) (println (present-board board :legend)))
            (recur (take-turn board (next-turn-fn board current-token opponent-token) current-token)
                   opponent
                   current-player))))))

(defn -main []
  (play (setup-new-game)))
