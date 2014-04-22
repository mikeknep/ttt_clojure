(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "a board"
  (it "is created with empty spots"
    (should= (repeat 9 nil) (create-board 3)))

  (it "has a number of spots equal to the square of the length"
    (should= 16 (count (create-board 4))))

  (it "returns the tokens at provided indexes"
    (let [indexes [0 2 4]
          spots   [:x nil :o nil nil]]
      (should= [:x :o nil] (values-at-indexes indexes spots)))))
