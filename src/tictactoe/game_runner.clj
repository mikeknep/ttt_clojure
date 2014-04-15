(ns tictactoe.game-runner
  (:require [tictactoe.rules :refer [game-over? current-token]]
            [tictactoe.console-io :refer [display-board display-spot declare-whose-turn declare-result]]
            [tictactoe.game :refer [get-player create-game]]
            [tictactoe.board :refer [update-board]]
            [tictactoe.gameplay :refer [take-turn]]))

(defn play-game [game]
  (loop [board            (get game :board)]
    (let [spots           (get board :spots)
          cur-token       (current-token spots)
          cur-player      (get-player game cur-token)
          next-turn-fn    (get cur-player :decision-maker)]
      (display-board board)
      (if (game-over? board)
        (declare-result board)
        (do (declare-whose-turn (display-spot cur-token))
        (recur (update-board (get board :size) (take-turn spots (next-turn-fn spots) cur-token))))))))

(defn -main []
  (play-game (create-game)))
