(ns tictactoe.simple-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.simple-ai :refer :all]))

(describe "simple ai"
  (context "selecting a random spot on a board"
    (it "selects an integer greater than zero"
      (let [spots (repeat 9 nil)]
        (should (>= (choose-random-spot spots) 0))))

    (it "selects an integer less than the total number of spots on the board"
      (let [spots (repeat 9 nil)]
        (should (<= (choose-random-spot spots) 9))))

    (it "does not select a spot that has been taken"
      (let [spots [nil nil :o nil nil nil nil nil nil]]
        (should-not= 2 (choose-random-spot spots))))))
