(ns tictactoe.console-io-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-io :refer :all]))

(describe "console io"

  (it "asks for a move and returns an integer"
    (should= 4 
      (with-in-str "4"
        (get-move))))

  (it "asks for a board size and returns an integer"
    (should= 3
      (with-in-str "3"
        (get-board-size))))

  (context "visually representing a board"
    (it "represents a spot played by :x as X"
      (should= "X" (display-spot :x)))

    (it "represents a spot played by :o as O"
      (should= "O" (display-spot :o)))

    (it "represents an unplayed spot as a blank space"
      (should= " " (display-spot nil)))

    (it "displays a pipe to separate spaces in a row"
      (should= "|" (pipe-or-newline 0 3)))

    (it "returns a new line to separate the space at the end of a row from the next row"
      (should= "\n" (pipe-or-newline 2 3)))

    (it "converts a new/empty board into a string representation of an empty board"
      (let [board   {:size 3 :spots (repeat 9 nil)}]
        (should= " | | \n | | \n | | \n" (format-board board))))

    (it "converts a board with spots that have been played into a string representation of a board with moves"
      (let [board   {:size 3 :spots [nil :x :o nil :x :o nil :o :x]}]
        (should= " |X|O\n |X|O\n |O|X\n" (format-board board))))

    (it "prints a formatted board"
      (let [board   {:size 3 :spots (repeat 9 nil)}]
        (should= (str (format-board board) "\n")
          (with-out-str (display-board board)))))))
