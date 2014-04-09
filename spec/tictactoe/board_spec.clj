(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "a board"
  (it "has total number of spaces equal to the square of the size"
    (should= 9 (count (spaces 3 {}))))

  (it "has 16 spaces if the size provided is 4"
    (should= 16 (count (spaces 4 {}))))

  (it "can have an x in the first spot"
    (should= :x (first (spaces 3 {0 :x}))))

  (it "can have an o in the last spot"
    (should= :o (last (spaces 3 {8 :o}))))

  (it "can have tokens in various spots"
    (should= [:x nil :o nil :o :x nil nil nil] (spaces 3 {0 :x 2 :o 4 :o 5 :x}))))
