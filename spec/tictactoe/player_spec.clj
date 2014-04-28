(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]
            [tictactoe.human-turn :refer [get-move]]))

(describe "a player"
  (it "is created with a token"
    (should= "X" ((create-player "human" "X") :token)))

  (it "is created with the simple ai's choose-random-spot function when it is an easy computer"
    (should= choose-random-spot ((create-player "easy computer" "X") :decision-maker)))

  (it "is created with the human-turn namespace's get-move function when it is a human player"
    (should= get-move ((create-player "human" "O") :decision-maker)))

  (it "is created with the unbeatable ai's choose-best-spot function when it is a hard computer"
    (should= choose-best-spot ((create-player "hard computer" "X") :decision-maker))))
