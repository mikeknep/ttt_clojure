(ns tictactoe.paths-spec
  (:require [speclj.core :refer :all]
            [tictactoe.paths :refer :all]))

(describe "paths"
  (context "on a 3x3 board"
    (it "has 8 total winning paths"
      (should== [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]] (all-winning-indexes 3))))

  (context "on a 4x4 board"
    (it "has 10 total winning paths"
      (should== [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15] [0 4 8 12] [1 5 9 13] [2 6 10 14] [3 7 11 15] [0 5 10 15] [3 6 9 12]] (all-winning-indexes 4)))))
