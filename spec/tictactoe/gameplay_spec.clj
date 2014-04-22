(ns tictactoe.gameplay-spec
  (:require [speclj.core :refer :all]
            [tictactoe.gameplay :refer :all]))

(describe "gameplay"
  (it "executes a valid move"
    (let [board [nil nil nil nil]
          index 3
          token :x]
      (should= [nil nil nil :x] (take-turn board index token))))

  (it "does not execute an invalid move"
    (let [board [nil nil nil :x]
          index 3
          token :o]
      (should= [nil nil nil :x] (take-turn board index token)))))
