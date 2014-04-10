(ns tictactoe.gameplay-spec
  (:require [speclj.core :refer :all]
            [tictactoe.gameplay :refer :all]))

(describe "gameplay"
  (it "executes a valid move"
    (let [spots [nil nil nil nil]
          index 3
          token "X"]
      (should= [nil nil nil "X"] (take-turn spots index token))))
  
  (it "does not execute an invalid move"
    (let [spots [nil nil nil "X"]
          index 3
          token "O"]
      (should= [nil nil nil "X"] (take-turn spots index token)))))
