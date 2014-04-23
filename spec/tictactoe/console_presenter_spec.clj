(ns tictactoe.console-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-presenter :refer :all]))

(describe "console-presenter"

  (context "presenting a board"
    (it "returns a pipe to separate spaces in a row"
      (should= "|" (border 0 3)))

    (it "returns a new line to separate the space at the end of a row from the next row"
      (should= "\n" (border 2 3)))

    (it "formats a new, empty board as a string"
      (let [board (repeat 9 nil)]
        (should= " | | \n | | \n | | \n" (format-board board))))

    (it "formats a board with spots that have been played into a string"
      (let [board [nil "X" "O"
                   nil "X" "O"
                   nil "O" "X"]]
        (should= " |X|O\n |X|O\n |O|X\n" (format-board board))))

    (it "prints a formatted board"
      (let [board (repeat 9 nil)]
        (should= (str "\n\n" (format-board board) "\n\n\n")
          (with-out-str (present-board board)))))

    (it "formats a legend of the available spaces' indexes"
      (let [board [nil "X" "O"
                   nil "X" "O"
                   nil "O" "X"]]
        (should= "0| | \n3| | \n6| | \n" (format-legend board)))))

  (context "presenting game conditions"
    (it "presents the current player"
      (should= "X's turn\n"
        (with-out-str (present-current-player "X"))))

    (it "presents a draw game result"
      (should= "Cat's game!\n"
        (with-out-str (present-draw))))

    (it "presents a winner"
      (should= "X wins!\n"
        (with-out-str (present-winner "X"))))

    (it "presents the correct game result"
      (let [board ["X"  "X"  "X"
                   nil nil nil
                   nil nil nil]]
        (should= "X wins!\n"
          (with-out-str (present-result board)))))))
