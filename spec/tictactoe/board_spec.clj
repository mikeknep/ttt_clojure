(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "a board"
  (it "has nine spaces"
    (should= 9 (count (spaces{}))))

  (it "can have an x in the first spot"
    (should= :x (first (spaces{0 :x}))))

  (it "can have an o in the last spot"
    (should= :o (last (spaces{8 :o}))))

  (it "can have tokens in various spots"
    (should= [:x nil :o nil :o :x nil nil nil] (spaces{0 :x 2 :o 4 :o 5 :x}))))
