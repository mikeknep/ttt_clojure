(ns tictactoe.game
  (:require [tictactoe.board :refer [create-board]]
            [tictactoe.console-io :refer [get-board-size get-player-decision-maker]]
            [tictactoe.player :refer [create-player]]))

(defn create-game []
  {:board     (create-board (get-board-size))
   :player-1  (create-player :x (get-player-decision-maker))
   :player-2  (create-player :o (get-player-decision-maker))})

(defn get-player [game token]
  (if (= :x token)
    (get game :player-1)
    (get game :player-2)))
