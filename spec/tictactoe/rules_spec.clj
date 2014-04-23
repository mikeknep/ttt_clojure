(ns tictactoe.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe.rules :refer :all]))

(describe "rules"
  (context "determining board size"
    (it "recognizes '3' as a valid board size"
      (should= true (valid-board-length? "3")))

    (it "does not recognize '3x3' as a valid board size"
      (should= false (valid-board-length? "3x3"))))

  (context "determining player types"
    (it "recognizes 'human' as a valid player type"
      (should= true (valid-player-type? "human")))

    (it "recognizes 'easy computer' as a valid player type"
      (should= true (valid-player-type? "easy computer")))

    (it "recognizes 'hard computer' as a valid player type"
      (should= true (valid-player-type? "hard computer"))))

  (context "determining player tokens"
    (it "recognizes a token that is more than one character length as invalid"
      (should= false (valid-token? "AB")))

    (it "recognizes a token that is the same as another player's token as invalid"
      (should= false (valid-token? "X" "X"))))

  (context "determining spot validity"
    (with board [nil "X" nil nil])
    (it "knows a spot is not valid if it has been played"
      (should= false (valid-spot? @board 1)))

    (it "knows a spot is not valid if it is less than 0"
      (should= false (valid-spot? @board -1)))

    (it "knows a spot is not valid if it is greater than or equal to the number of spots"
      (should= false (valid-spot? @board 4))))

  (it "returns the available spots"
    (let [board ["X" nil nil "O"]]
      (should= [1 2] (available-spots board))))


  (context "checking for a draw"
    (it "recognizes all spots as being taken"
      (let [board ["X" "O" "X" "O"]]
        (should= true (all-spots-taken? board))))

    (it "recognizes spots still open to be played"
      (let [board ["X" "O" "X" nil]]
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
    (let [board ["O" "O"  "O"
                 "X" nil "X"
                 "X" nil "X"]]
      (should= "O" (get-winner board)))))
