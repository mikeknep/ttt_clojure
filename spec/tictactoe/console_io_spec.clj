(ns tictactoe.console-io-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-io :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]))

(describe "console io"
  (around [it]
    (with-out-str (it)))

  (it "asks for a move and returns an integer"
    (should= 3
      (with-in-str "3"
        (get-move [nil "X" nil nil]))))

  (it "asks for a board length and returns an integer"
    (should= 3
      (with-in-str "3"
        (get-board-length))))

  (context "defining player types"
    (it "assigns the console-io get-move function to a human player"
      (should= get-move
        (with-in-str "human"
          (get-player-decision-maker))))

    (it "assigns the simple ai choose-random-move function to an easy computer player"
      (should= choose-random-spot
        (with-in-str "easy computer"
          (get-player-decision-maker))))

    (it "assigns the unbeatable ai choose-best-move function to a hard computer player"
      (should= choose-best-spot
        (with-in-str "hard computer"
          (get-player-decision-maker)))))

  (context "asking to play again"
    (it "returns true if the human wants to play again"
      (should= true (with-in-str "y" (play-again?))))

    (it "returns false if the human does not want to play again"
      (should= false (with-in-str "n" (play-again?))))))
