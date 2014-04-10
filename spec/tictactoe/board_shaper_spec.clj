(ns tictactoe.board-shaper-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board-shaper :refer :all]))

(describe "board-shaper"
  (it "determines the indexes of rows on a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8]] (row-indexes 3)))

  (it "determines the indexes of columns on a 3x3 board"
    (should= [[0 3 6] [1 4 7] [2 5 8]] (column-indexes 3)))

  (it "determines the indexes of diagonals on a 3x3 board"
    (should= [[0 4 8] [2 4 6]] (diagonal-indexes 3)))

  (it "returns the indexes of all winning paths on a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]] (all-winning-indexes 3))))
