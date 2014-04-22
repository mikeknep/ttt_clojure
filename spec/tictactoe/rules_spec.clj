(ns tictactoe.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe.rules :refer :all]))

(describe "rules"
  (context "determining board size"
    (it "recognizes '3' as a valid board size"
      (should= true (valid-board-size? "3")))

    (it "does not recognize '3x3' as a valid board size"
      (should= false (valid-board-size? "3x3"))))

  (context "determining player types"
    (it "recognizes 'human' as a valid player type"
      (should= true (valid-player-type? "human")))

    (it "recognizes 'easy computer' as a valid player type"
      (should= true (valid-player-type? "easy computer")))

    (it "recognizes 'hard computer' as a valid player type"
      (should= true (valid-player-type? "hard computer"))))

  (context "determining spot validity"
    (with spots [nil :x nil nil])
    (it "knows a spot is not valid if it has been played"
      (should= false (valid-spot? @spots 1)))

    (it "knows a spot is not valid if it is less than 0"
      (should= false (valid-spot? @spots -1)))

    (it "knows a spot is not valid if it is greater than or equal to the number of spots"
      (should= false (valid-spot? @spots 4))))

  (it "returns the available spots"
    (let [spots [:x nil nil :o]]
      (should= [1 2] (available-spots spots))))

  (context "determining whose turn it is"
    (it "sets current token as :x for the first turn"
      (let [spots (repeat 9 nil)]
        (should= :x (current-token spots))))

    (it "sets current token as :o when :x has played more turns than :o"
      (let [spots [:x nil nil nil]]
        (should= :o (current-token spots))))

    (it "sets current token as :x when :x and :o have played an equal number of turns"
      (let [spots [:x :o nil nil]]
        (should= :x (current-token spots))))

    (it "sets the opponent token as :o when it is :x's turn"
      (let [spots [:x :o nil nil]]
        (should= :o (opponent-token spots)))))

  (context "checking for a draw"
    (it "recognizes all spots as being taken"
      (let [spots [:x :o :x :o]]
        (should= true (all-spots-taken? spots))))

    (it "recognizes spots still open to be played"
      (let [spots [:x :o :z nil]]
        (should= false (all-spots-taken? spots)))))


  (context "checking a single path for a winner"
    (it "returns true for a winning collection"
      (let [tokens  [:x :x :x]]
        (should= true (winner-in-collection? tokens))))

    (it "returns false if a collection has multiple tokens"
      (let [tokens [:x :o :x]]
        (should= false (winner-in-collection? tokens))))

    (it "returns false if a collection has all empty spaces"
      (let [tokens [nil nil nil]]
        (should= false (winner-in-collection? tokens)))))


  (context "checking the whole board for a winner"
    (it "returns true for a board with a winner"
      (let [board {:size 3 :spots [:x nil nil :x nil nil :x nil nil]}]
        (should= true (winner-on-board? board))))

    (it "returns false for a board with no winner"
      (let [board {:size 3 :spots (repeat 9 nil)}]
        (should= false (winner-on-board? board)))))


  (context "declaring the game over"
    (it "declares the game to be over when there is a draw"
      (let [board {:size 3 :spots [:x :o :x :x :o :x :o :x :o]}]
        (should= true (game-over? board))))

    (it "declares the game to be over when there is a winner"
      (let [board {:size 3 :spots [:o :o :o :x nil :x :x nil :x]}]
        (should= true (game-over? board)))))

  (it "gets the winning token from a board with a winner"
    (let [board {:size 3 :spots [:o :o :o :x nil :x :x nil :x]}]
      (should= :o (get-winner board)))))
