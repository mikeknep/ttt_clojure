(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]
            [tictactoe.human-turn :refer [get-move]]))

(describe "a player"
  (with human         (create-player "human" "X"))
  (with easy-computer (create-player "easy computer" "O"))
  (with hard-computer (create-player "hard computer" "Z"))

  (it "is created with a token"
    (should= "X" (get @human :token)))

  (it "is created with the simple ai's choose-random-spot function when it is an easy computer"
    (should= choose-random-spot (@easy-computer :decision-maker)))

  (it "is created with the human-turn namespace's get-move function when it is a human player"
    (should= get-move (@human :decision-maker)))

  (it "is created with the unbeatable ai's choose-best-spot function when it is a hard computer"
    (should= choose-best-spot (@hard-computer :decision-maker)))

  (it "is human if it's decision-maker is get-move"
    (should= true (human? @human)))

  (it "is not a human if it's decision-maker is not get-move"
    (should= false (human? @hard-computer))))
