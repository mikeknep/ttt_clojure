(ns tictactoe.setup
  (:require [tictactoe.board :refer [create-board]]
            [tictactoe.player :refer [create-player]]
            [tictactoe.console.text-presenter :refer [get-player-type get-player-token]]
            [tictactoe.console.io :refer [prompt]]))

(defn setup-new-game []
  {:board (create-board 3)

   :player-1  (create-player (prompt get-player-type)
                             (prompt get-player-token))

   :player-2  (create-player (prompt get-player-type)
                             (prompt get-player-token))})
