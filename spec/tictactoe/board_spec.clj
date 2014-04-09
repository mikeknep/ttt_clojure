(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "a board"
  (it "is created with a length"
    (should= 3 ((create-board 3) :length)))

  (it "is created with empty spots"
    (should= (repeat 9 nil) ((create-board 3) :spots)))

  (it "has a number of spots equal to the square of the length"
    (should= 16 (count ((create-board 4) :spots)))))
