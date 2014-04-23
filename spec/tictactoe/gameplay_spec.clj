(ns tictactoe.gameplay-spec
  (:require [speclj.core :refer :all]
            [tictactoe.gameplay :refer :all]))

(describe "gameplay"
  (it "executes a valid move"
    (let [board [nil nil nil nil]
          index 3
          token "X"]
      (should= [nil nil nil "X"] (take-turn board index token))))

  (it "does not execute an invalid move"
    (let [board [nil nil nil "X"]
          index 3
          token "X"]
      (should= [nil nil nil "X"] (take-turn board index token)))))
