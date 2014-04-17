(ns tictactoe.simple-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.simple-ai :refer :all]))

(describe "simple ai"
  (with board {:size 3 :spots [nil nil :o
                               nil nil nil
                               nil nil nil]})

  (context "selecting a random spot on a board"
    (it "selects an integer greater than zero"
      (should (>= (choose-random-spot @board) 0)))

    (it "selects an integer less than the total number of spots on the board"
      (should (<= (choose-random-spot @board) 9)))

    (it "does not select a spot that has been taken"
      (should-not= 2 (choose-random-spot @board)))))
