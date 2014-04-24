(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "unbeatable ai"
  (with draw      ["X" "O" "X"
                   "X" "O" "X"
                   "O" "X" "O"])

  (with basic-win ["X" "X" "X"
                   nil nil nil
                   nil nil nil])

  (context "basic scoring of a board"
    (it "scores a board with a cat's game 0.0"
      (should= 0.0 (simple-score @draw)))

    (it "scores a board with a winner 1.0"
      (should= 1.0 (simple-score @basic-win))))


  (context "scoring a board with depth"
    (it "scores a winning board at zero depth level +Infinity"
      (should= (/ 1.0 0) (score-with-depth @basic-win 0)))

    (it "scores a winning board at one depth level -1.0"
      (should= -1.0 (score-with-depth @basic-win 1)))

    (it "scores a winning board at two depth levels +0.5"
      (should= 0.5 (score-with-depth @basic-win 2)))

    (it "scores a draw board at any depth 0"
      (should= 0 (score-with-depth @draw 0))))


  (context "creating hypothetical future boards"
    (it "creates a board with an updated spot"
      (let [board ["X" nil nil
                   nil nil nil
                   nil nil nil]]
        (should= ["X" "O" nil
                  nil nil nil
                  nil nil nil]
          (create-altered-board board 1 "O"))))

    (it "creates all potential child boards"
      (let [board ["X" "X" nil
                   "O" "O" nil
                   "X" "O" nil]]
        (should= [["X" "X" "X"
                   "O" "O" nil
                   "X" "O" nil]

                  ["X" "X" nil
                   "O" "O" "X"
                   "X" "O" nil]

                  ["X" "X" nil
                   "O" "O" nil
                   "X" "O" "X"]]
          (child-boards board "X")))))

  (context "determining min or max"
    (it "applies the min function if depth is even"
      (should= min (min-or-max 2)))

    (it "applies the max function if depth is odd"
      (should= max (min-or-max 3))))

  (context "the opening move"
    (it "knows when it is player 1's first move"
      (let [board [nil nil nil
                   nil nil nil
                   nil nil nil]]
        (should= true (first-move? board))))

    (it "knows when it is player 2's first move"
      (let [board ["x" nil nil
                   nil nil nil
                   nil nil nil]]
        (should= true (first-move? board))))

    (it "chooses the middle spot (4) on a 3x3 board if it is open"
      (let [board [nil nil nil
                   nil nil nil
                   nil nil nil]]
        (should= 4 (choose-first-move board))))

    (it "chooses the top corner spot (0) on a 3x3 board if the middle is taken"
      (let [board [nil nil nil
                   nil "x" nil
                   nil nil nil]]
        (should= 0 (choose-first-move board))))

    (it "chooses spot 8 on a 4x4 board if it is open"
      (let [board [nil nil nil nil
                   nil nil nil nil
                   nil nil nil nil
                   nil nil nil nil]]
        (should= 8 (choose-first-move board))))

    (it "chooses spot 0 on a 4x4 board if spot 8 is taken"
      (let [board [nil nil nil nil
                   nil nil nil nil
                   "x" nil nil nil
                   nil nil nil nil]]
        (should= 0 (choose-first-move board)))))


  ;
  ; High-level minimax testing
  ;
  (context "analyzing the board"
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
          (should= 1 (choose-best-spot board "X" "O")))))))
