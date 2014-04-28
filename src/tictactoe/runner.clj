(ns tictactoe.runner
  (:require [tictactoe.rules :refer [game-over?]]
            [tictactoe.console.prompter :refer [prompt print-with-padding]]
            [tictactoe.console.text-presenter :refer [present-current-player present-result get-play-again]]
            [tictactoe.console.board-presenter :refer [present-board]]
            [tictactoe.gameplay :refer [take-turn]]
            [tictactoe.setup :refer [setup-new-game]]))

(defn play [new-game]
  (loop [board          (get new-game :board)
         current-player (get new-game :player-1)
         opponent       (get new-game :player-2)]
    (let [current-token   (get current-player :token)
          opponent-token  (get opponent :token)
          next-turn-fn    (get current-player :decision-maker)]
      (print-with-padding (present-board board :traditional))
      (if (game-over? board)
        (do (println (present-result board))
            (if (prompt get-play-again) (play (setup-new-game))))
        (do (println (present-current-player current-token))
            (recur (take-turn board (next-turn-fn board current-token opponent-token) current-token)
                   opponent
                   current-player))))))

(defn -main []
  (play (setup-new-game)))
