(ns tictactoe.simple-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.simple-ai :refer :all]))

(describe "simple ai"
  (it "selects the first spot on an empty board"
    (let [spots (repeat 9 nil)]
      (should= 0 (choose-spot spots))))
  
  (it "selects the first available spot"
    (let [spots [:x :x :o nil nil nil nil nil nil]]
      (should= 3 (choose-spot spots)))))
