(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "unbeatable ai"
  (with draw-board      {:size 3 :spots [:x :o :x :x :o :x :o :x :o]})
  (with basic-win-board {:size 3 :spots [:x :x :x nil nil nil nil nil nil]})

  (context "basic scoring of a board"
    (it "scores a board with a cat's game 0.0"
      (should= 0.0 (simple-board-score @draw-board)))

    (it "scores a board with a winner 1.0"
      (should= 1.0 (simple-board-score @basic-win-board))))

  (context "scoring a board with depth"
    (it "scores a winning board at zero depth level +Infinity"
      (should= (/ 1.0 0) (score-board-with-depth @basic-win-board 0)))

    (it "scores a winning board at one depth level -1.0"
      (should= -1.0 (score-board-with-depth @basic-win-board 1)))

    (it "scores a winning board at two depth levels +0.5"
      (should= 0.5 (score-board-with-depth @basic-win-board 2)))

    (it "scores a draw board at any depth 0"
      (should= 0 (score-board-with-depth @draw-board 0))))

  (context "updating the best score"
    (it "changes the best score if the new score is better than the existing best score"
      (should= 0.5 (update-best-score 0.5 -0.25)))

    (it "doesn't change the best score if the new score is worse than the existing best score"
      (should= -0.25 (update-best-score -0.5 -0.25))))

  (context "updating the best move"
    (it "changes the best move if the new move has a better score than the existing best move"
      (should= 5 (update-best-spot 5 1.0 2 0.5)))

    (it "does not change the best move if the new move has a worse score than the existing best move"
      (should= 3 (update-best-spot 6 0.25 3 0.5)))))
