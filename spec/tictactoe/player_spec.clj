(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.console-io :refer [get-move]]))

(describe "a player"
  (it "is created with a token"
    (should= :x ((create-player :x choose-random-spot) :token)))

  (it "is created with the simple ai's choose-random-spot function when it is an easy computer"
    (should= choose-random-spot ((create-player :x choose-random-spot) :decision-maker)))

  (it "is created with the console io's get-move function when it is a human player"
    (should= get-move ((create-player :o get-move) :decision-maker))))
