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

  (context "determining spot validity"
    (with board [nil :x nil nil])
    (it "knows a spot is not valid if it has been played"
      (should= false (valid-spot? @board 1)))

    (it "knows a spot is not valid if it is less than 0"
      (should= false (valid-spot? @board -1)))

    (it "knows a spot is not valid if it is greater than or equal to the number of spots"
      (should= false (valid-spot? @board 4))))

  (it "returns the available spots"
    (let [board [:x nil nil :o]]
      (should= [1 2] (available-spots board))))

  (context "determining whose turn it is"
    (it "sets current token as :x for the first turn"
      (let [board (repeat 9 nil)]
        (should= :x (current-token board))))

    (it "sets current token as :o when :x has played more turns than :o"
      (let [board [:x nil nil nil]]
        (should= :o (current-token board))))

    (it "sets current token as :x when :x and :o have played an equal number of turns"
      (let [board [:x :o nil nil]]
        (should= :x (current-token board))))

    (it "sets the opponent token as :o when it is :x's turn"
      (let [board [:x :o nil nil]]
        (should= :o (opponent-token board)))))

  (context "checking for a draw"
    (it "recognizes all spots as being taken"
      (let [board [:x :o :x :o]]
        (should= true (all-spots-taken? board))))

    (it "recognizes spots still open to be played"
      (let [board [:x :o :z nil]]
        (should= false (all-spots-taken? board)))))


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
      (let [board [:x nil nil
                   :x nil nil
                   :x nil nil]]
        (should= true (winner-present? board))))

    (it "returns false for a board with no winner"
      (let [board (repeat 9 nil)]
        (should= false (winner-present? board)))))


  (context "declaring the game over"
    (it "declares the game to be over when there is a draw"
      (let [board [:x :o :x
                   :x :o :x
                   :o :x :o]]
        (should= true (game-over? board))))

    (it "declares the game to be over when there is a winner"
      (let [board [:o :o  :o
                   :x nil :x
                   :x nil :x]]
        (should= true (game-over? board)))))

  (it "gets the winning token from a board with a winner"
    (let [board [:o :o  :o
                 :x nil :x
                 :x nil :x]]
      (should= :o (get-winner board)))))
