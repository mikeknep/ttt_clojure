(ns tictactoe.game-runner
  (:require [tictactoe.rules :refer [game-over? current-token opponent-token]]
            [tictactoe.console-io :refer [display-board display-spot declare-whose-turn declare-result]]
            [tictactoe.game :refer [get-player create-game]]
            [tictactoe.board :refer [update-board]]
            [tictactoe.gameplay :refer [take-turn]]))

(defn play-game [game]
  (loop [board            (get game :board)]
    (let [spots           (get board :spots)
          current-token   (current-token spots)
          opponent-token  (opponent-token spots)
          current-player  (get-player game current-token)
          next-turn-fn    (get current-player :decision-maker)]
      (display-board board)
      (if (game-over? board)
        (declare-result board)
        (do (declare-whose-turn (display-spot current-token))
        (recur (update-board (get board :size) (take-turn spots (next-turn-fn board current-token opponent-token) current-token))))))))

(defn -main []
  (play-game (create-game)))
