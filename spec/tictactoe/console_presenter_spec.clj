(ns tictactoe.console-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-presenter :refer :all]))

(describe "console-presenter"

  (context "presenting a board"
    (it "displays a pipe to separate spaces in a row"
      (should= "|" (border 0 3)))

    (it "returns a new line to separate the space at the end of a row from the next row"
      (should= "\n" (border 2 3)))

    (it "converts a new/empty board into a string representation of an empty board"
      (let [board (repeat 9 nil)]
        (should= " | | \n | | \n | | \n" (format-board board))))

    (it "converts a board with spots that have been played into a string representation of a board with moves"
      (let [board [nil "X" "O"
                   nil "X" "O"
                   nil "O" "X"]]
        (should= " |X|O\n |X|O\n |O|X\n" (format-board board))))

    (it "prints a formatted board"
      (let [board (repeat 9 nil)]
        (should= (str "\n\n" (format-board board) "\n\n\n")
          (with-out-str (display-board board)))))

    (it "converts a board into a string legend representing the available spaces"
      (let [board [nil "X" "O"
                   nil "X" "O"
                   nil "O" "X"]]
        (should= "0| | \n3| | \n6| | \n" (format-legend board)))))

  (context "presenting game conditions"
    (it "says whose turn it is"
      (should= "X's turn\n"
        (with-out-str (declare-whose-turn "X"))))

    (it "declares a draw"
      (should= "Cat's game!\n"
        (with-out-str (declare-draw))))

    (it "declares a winner"
      (should= "X wins!\n"
        (with-out-str (declare-winner "X"))))

    (it "declares the correct result"
      (let [board ["X"  "X"  "X"
                   nil nil nil
                   nil nil nil]]
        (should= "X wins!\n"
          (with-out-str (declare-result board)))))))
