(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "a board"
  (it "is created with empty spots"
    (should= [nil] (distinct (create-board 3))))

  (it "has a number of spots equal to the square of the length"
    (should= 16 (count (create-board 4))))

  (it "has a length equal to the square root of the number of spots"
    (should= 3 (board-length (create-board 3))))

  (it "returns the tokens at provided indexes"
    (let [indexes [0 2 4]
          board   [:x nil :o nil nil]]
      (should= [:x :o nil] (values-at-indexes indexes board)))))
