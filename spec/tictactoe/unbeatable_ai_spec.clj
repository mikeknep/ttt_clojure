(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "unbeatable ai"
  (context "when there is only one open spot remaining"
    (it "chooses the last available spot on the board when it leads to a draw"
      (let [board [nil "X" "O"
                   "O" "X" "X"
                   "X" "O" "O"]]
        (should= 0 (choose-best-spot board "X" "O"))))

    (it "chooses the last available spot on the board when it leads to a win"
      (let [board ["X" "O" "X"
                   "O" "X" "O"
                   "O" "X" nil]]
        (should= 8 (choose-best-spot board "X" "O")))))

  (context "when there are multiple spots remaining, one of which leads to victory"
    (it "chooses the spot that leads to immediate victory (version 1)"
      (let [board ["X" nil "O"
                   "O" "X" nil
                   "X" "O" nil]]
        (should= 8 (choose-best-spot board "X" "O"))))

    (it "chooses the spot that leads to immediate victory (version 2)"
      (let [board ["X" "O" "X"
                   "O" "O" nil
                   "X" nil "X"]]
        (should= 5 (choose-best-spot board "O" "X")))))

  (context "blocking opponent victories"
    (it "blocks the opponent from winning on the next turn (version 1)"
      (let [board ["O" "X" nil
                   nil "X" nil
                   nil nil nil]]
        (should= 7 (choose-best-spot board "O" "X"))))

    (it "blocks the opponent from winning on the next turn (version 2)"
      (let [board ["X" "O" "X"
                   "X" "O" nil
                   "O" nil nil]]
        (should= 7 (choose-best-spot board "X" "O"))))

    (it "blocks the opponent from winning on the next turn (version 3)"
      (let [board ["X" nil "O"
                   "O" "O" "X"
                   "X" "X" nil]]
        (should= 8 (choose-best-spot board "O" "X"))))

    (it "blocks the opponent from winning on the next turn (version 4)"
      (let [board [nil nil nil
                   "X" "O" nil
                   nil "O" "X"]]
        (should= 1 (choose-best-spot board "X" "O"))))))
