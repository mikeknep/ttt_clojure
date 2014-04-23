(ns tictactoe.player
  (:require [tictactoe.console-io :refer [get-move]]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]))

(defn create-player [decision-maker token]
  {:token token
   :decision-maker decision-maker})

(defn set-player-decision-maker [player-type]
  (case player-type
    "human"           get-move
    "easy computer"   choose-random-spot
    "hard computer"   choose-best-spot))
