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
        (get-move {:size 2 :spots [nil :x nil nil]}))))

  (it "asks for a board size and returns an integer"
    (should= 3
      (with-in-str "3"
        (get-board-size))))

  (it "says whose turn it is"
    (should= "X's turn\n"
      (with-out-str (declare-whose-turn "X"))))

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
        (should= (str "\n\n" (format-board board) "\n\n\n")
          (with-out-str (display-board board))))))

  (context "declaring the outcome of the game"
    (it "declares a draw"
      (should= "Cat's game!\n"
        (with-out-str (declare-draw))))

    (it "declares a winner"
      (should= "X wins!\n"
        (with-out-str (declare-winner :x))))

    (it "declares the correct result"
      (let [board   {:size 3 :spots [:x :x :x nil nil nil nil nil nil]}]
        (should= "X wins!\n"
          (with-out-str (declare-result board)))))))
