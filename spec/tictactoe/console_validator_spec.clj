(ns tictactoe.console-validator-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-validator :refer :all]))

(describe "console-validator"
  (context "determining player types"
    (it "recognizes 'human' as a valid player type"
      (should= true (valid-player-type? "human")))

    (it "recognizes 'easy computer' as a valid player type"
      (should= true (valid-player-type? "easy computer")))

    (it "recognizes 'hard computer' as a valid player type"
      (should= true (valid-player-type? "hard computer"))))

  (context "determining player tokens"
    (it "recognizes a token that is more than one character length as invalid"
      (should= false (valid-token? "AB"))))

  (context "determining if playing again"
    (it "knows 'y' is a valid play-again response"
      (should= true (valid-play-again-response? "y")))))
