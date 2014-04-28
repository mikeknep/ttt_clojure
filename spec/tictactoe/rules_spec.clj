(ns tictactoe.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe.rules :refer :all]))

(describe "rules"
  (context "determining spot validity"
    (with board [nil "X" nil
                 nil nil nil
                 nil nil nil])
    (it "knows a spot is not valid if it has been played"
      (should= false (valid-spot? @board 1)))

    (it "knows a spot is not valid if it is less than 0"
      (should= false (valid-spot? @board -1)))

    (it "knows a spot is not valid if it is greater than or equal to the number of spots"
      (should= false (valid-spot? @board 10))))

  (it "recognizes the first move of a game"
    (let [board [nil nil nil
                 nil nil nil
                 nil nil nil]]
      (should= true (first-move? board))))

  (it "returns the available spots"
    (let [board ["X" nil nil
                 "O" "X" "O"
                 "X" "X" "O"]]
      (should= [1 2] (available-spots board))))

  (context "checking for a draw"
    (it "recognizes all spots as being taken"
      (let [board ["X" "O" "X"
                   "X" "O" "X"
                   "O" "X" "O"]]
        (should= true (all-spots-taken? board))))

    (it "recognizes spots still open to be played"
      (let [board ["X" "O" "X"
                   nil nil nil
                   nil nil nil]]
        (should= false (all-spots-taken? board)))))


  (context "checking a single path for a winner"
    (it "returns true for a winning collection"
      (let [tokens  ["X" "X" "X"]]
        (should= true (winner-in-collection? tokens))))

    (it "returns false if a collection has multiple tokens"
      (let [tokens ["X" "O" "X"]]
        (should= false (winner-in-collection? tokens))))

    (it "returns false if a collection has all empty spaces"
      (let [tokens [nil nil nil]]
        (should= false (winner-in-collection? tokens)))))


  (context "checking the whole board for a winner"
    (it "returns true for a board with a winner"
      (let [board ["X" nil nil
                   "X" nil nil
                   "X" nil nil]]
        (should= true (winner-present? board))))

    (it "returns false for a board with no winner"
      (let [board (repeat 9 nil)]
        (should= false (winner-present? board)))))


  (context "declaring the game over"
    (it "declares the game to be over when there is a draw"
      (let [board ["X" "O" "X"
                   "X" "O" "X"
                   "O" "X" "O"]]
        (should= true (game-over? board))))

    (it "declares the game to be over when there is a winner"
      (let [board ["O" "O"  "O"
                   "X" nil "X"
                   "X" nil "X"]]
        (should= true (game-over? board)))))

  (it "gets the winning token from a board with a winner"
    (let [board ["O" "O" "O"
                 "X" nil "X"
                 "X" nil "X"]]
      (should= "O" (get-winner board)))))
