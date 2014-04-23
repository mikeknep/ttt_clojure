(ns tictactoe.setup
  (:require [tictactoe.board :refer [create-board]]
            [tictactoe.player :refer [create-player]]
            [tictactoe.console-prompter :refer [prompt get-board-length get-player-type get-player-token]]))

(defn setup-new-game []
  {:board (create-board (prompt get-board-length))

   :player-1  (create-player (prompt get-player-type)
                             (prompt get-player-token))

   :player-2  (create-player (prompt get-player-type)
                             (prompt get-player-token))})
