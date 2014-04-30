(ns tictactoe.console.text-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console.text-presenter :refer :all]))

(describe "text-presenter"
  (describe "presenting a string with a token"
    (it "returns a string that includes the current player's token"
      (should-contain "X" (present-current-player "X")))

    (it "returns a string that includes the winning player's token"
      (should-contain "X" (present-winner "X"))))

  (describe "presenting the result of the game"
    (it "returns a string that includes the winner's token"
      (let [board ["X" "X" "X"
                   nil nil nil
                   nil nil nil]]
        (should-contain "X" (present-result board))))

    (it "returns a string that includes a draw message"
      (let [board ["X" "O" "X"
                   "X" "O" "X"
                   "O" "X" "O"]]
        (should-contain #"[[D|d]raw|[C|c]at]" (present-result board)))))

  (describe "generating prompt templates"
    (context "for getting player type"
      (it "accepts an input of 1, 2, or 3"
        (should= true ((get-player-type :validity-checker) "1")))

      (it "does not accept an input of 4"
        (should= false ((get-player-type :validity-checker) "4")))

      (it "returns 'easy computer' for an input of '2'"
        (should= "easy computer" ((get-player-type :followup-fn) "2"))))

    (context "for getting player token"
      (it "accepts a one-character token"
        (should= true ((get-player-token :validity-checker) "C")))

      (it "does not accept a multi-character token"
        (should= false ((get-player-token :validity-checker) "XX")))

      (it "returns the token"
        (should= "M" ((get-player-token :followup-fn) "M"))))

    (context "for getting play again"
      (it "accepts an input of 1 or 2"
        (should= true ((get-play-again :validity-checker) "1")))

      (it "does not accept an input of 'yes'"
        (should= false ((get-play-again :validity-checker) "yes")))

      (it "returns true when the user inputs 1"
        (should= true ((get-play-again :followup-fn) "1")))

      (it "returns false when the user inputs 2"
        (should= false ((get-play-again :followup-fn) "2"))))))
