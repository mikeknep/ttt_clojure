(ns tictactoe.simple_ai_spec
  (:require [speclj.core :refer :all]
            [tictactoe.simple_ai :refer :all]))

(describe "simple ai"
  (it "selects the first spot on an empty board"
    (let [board {:size 3 :spots (repeat 9 nil)}]
      (should= 0 (choose-spot board))))
  
  (it "selects the first available spot"
    (let [board {:size 3 :spots [:x :x :o nil nil nil nil nil nil]}]
      (should= 3 (choose-spot board)))))
