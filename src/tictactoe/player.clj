(ns tictactoe.player
  (:require [tictactoe.human-turn :refer [get-move]]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]))

(defn create-player [player-type token]
  {:token token
   :type player-type
   :decision-maker (case player-type
                     "human"          get-move
                     "easy computer"  choose-random-spot
                     "hard computer"  choose-best-spot)})

(defn human? [player]
  (= "human" (player :type)))
