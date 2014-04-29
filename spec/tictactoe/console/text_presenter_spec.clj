(ns tictactoe.console.text-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console.text-presenter :refer :all]))

(describe "text-presenter"
  (describe "presenting strings about game conditions"
    (it "returns a string announcing the current player"
      (should= "X's turn" (present-current-player "X")))

    (it "returns a string announcing a draw"
      (should= "Cat's game!" (present-draw)))

    (it "returns a string announcing a player has won"
      (should= "X wins!" (present-winner "X")))

    (it "returns the appropriate string for a game result"
      (let [board ["X" "X" "X"
                   nil nil nil
                   nil nil nil]]
        (should= "X wins!" (present-result board)))))


  (describe "parsing a file"
    (with mock-file "mock.txt")

    (it "returns a line from a file"
      (should= "Hello world!" (parse @mock-file 0)))

    (it "only returns one line"
      (should= "This is a separate line." (parse @mock-file 1)))))
