(ns tictactoe.board-shaper-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board-shaper :refer :all]))

(describe "board-shaper"
  (context "shaping a 3x3 board"
    (it "determines the row indexes"
      (should= [[0 1 2] [3 4 5] [6 7 8]] (row-indexes 3)))

    (it "determines the column indexes"
      (should= [[0 3 6] [1 4 7] [2 5 8]] (column-indexes 3)))

    (it "determines the diagonal indexes"
      (should= [[0 4 8] [2 4 6]] (diagonal-indexes 3)))

    (it "returns the indexes of all winning paths"
      (should= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]] (all-winning-indexes 3))))

  (context "shaping a 4x4 board"
    (it "determines the row indexes"
      (should= [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]] (row-indexes 4)))

    (it "determines the column indexes"
      (should= [[0 4 8 12] [1 5 9 13] [2 6 10 14] [3 7 11 15]] (column-indexes 4)))

    (it "determines the diagonal indexes"
      (should= [[0 5 10 15] [3 6 9 12]] (diagonal-indexes 4)))

    (it "returns the indexes of all winning paths"
      (should= [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15] [0 4 8 12] [1 5 9 13] [2 6 10 14] [3 7 11 15] [0 5 10 15] [3 6 9 12]] (all-winning-indexes 4)))))
