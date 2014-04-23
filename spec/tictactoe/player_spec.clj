(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.human-turn :refer [get-move]]))

(describe "a player"
  (it "is created with a token"
    (should= "X" ((create-player choose-random-spot "X") :token)))

  (it "is created with the simple ai's choose-random-spot function when it is an easy computer"
    (should= choose-random-spot ((create-player choose-random-spot "X") :decision-maker)))

  (it "is created with the console io's get-move function when it is a human player"
    (should= get-move ((create-player get-move "O") :decision-maker)))

  (it "returns the get-move function for a human"
    (should= get-move (set-player-decision-maker "human"))))
