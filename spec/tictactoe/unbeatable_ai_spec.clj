(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "unbeatable ai"
  (with draw      [:x :o :x
                   :x :o :x
                   :o :x :o])

  (with basic-win [:x  :x  :x
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

  (context "updating the best score"
    (it "changes the best score if the new score is better than the existing best score"
      (should= 0.5 (update-best-score 0.5 -0.25)))

    (it "doesn't change the best score if the new score is worse than the existing best score"
      (should= -0.25 (update-best-score -0.5 -0.25))))

  (context "updating the best move"
    (it "changes the best move if the new move has a better score than the existing best move"
      (should= 5 (update-best-spot 5 1.0 2 0.5)))

    (it "does not change the best move if the new move has a worse score than the existing best move"
      (should= 3 (update-best-spot 6 0.25 3 0.5))))

  (context "creating hypothetical future boards"
    (it "creates a board with an updated spot"
      (let [spots [:x  nil nil
                   nil nil nil
                   nil nil nil]]
        (should= [:x  :o  nil
                  nil nil nil
                  nil nil nil]
          (create-altered-board spots 1 :o))))

    (it "creates all potential child boards"
      (let [spots [:x  :x  nil
                   :o  :o  nil
                   :x  :o  nil]]
        (should= [[:x :x :x
                   :o :o nil
                   :x :o nil]

                  [:x :x nil
                   :o :o :x
                   :x :o nil]

                  [:x :x nil
                   :o :o nil
                   :x :o :x]]
          (child-boards spots :x)))))

  (context "determining min or max"
    (it "applies the min function if depth is even"
      (should= min (min-or-max 2)))

    (it "applies the max function if depth is odd"
      (should= max (min-or-max 3))))



  ;
  ; High-level minimax testing
  ;
  (context "analyzing the board"
    (context "when there is only one open spot remaining"
      (it "chooses the last available spot on the board when it leads to a draw"
        (let [spots [nil :x :o
                     :o  :x :x
                     :x  :o :o]]
          (should= 0 (choose-best-spot spots :x :o))))

      (it "chooses the last available spot on the board when it leads to a win"
        (let [spots [:x :o :x
                     :o :x :o
                     :o :x nil]]
          (should= 8 (choose-best-spot spots :x :o)))))

    (context "when there are multiple spots remaining, one of which leads to victory"
      (it "chooses the spot that leads to immediate victory (version 1)"
        (let [spots [:x nil :o
                     :o :x nil
                     :x :o nil]]
          (should= 8 (choose-best-spot spots :x :o))))

      (it "chooses the spot that leads to immediate victory (version 2)"
        (let [spots [:x  :o  :x
                     :o  :o  nil
                     :x  nil :x]]
          (should= 5 (choose-best-spot spots :o :x)))))

    (context "blocking opponent victories"
      (it "blocks the opponent from winning on the next turn (version 1)"
        (let [spots [:o  :x  nil
                     nil :x  nil
                     nil nil nil]]
          (should= 7 (choose-best-spot spots :o :x))))

      (it "blocks the opponent from winning on the next turn (version 2)"
        (let [spots [:x  :o   :x
                     :x  :o   nil
                     :o  nil  nil]]
          (should= 7 (choose-best-spot spots :x :o))))

      (it "blocks the opponent from winning on the next turn (version 3)"
        (let [spots [:x nil :o
                     :o :o  :x
                     :x :x  nil]]
          (should= 8 (choose-best-spot spots :o :x))))

      (it "blocks the opponent from winning on the next turn (version 4)"
        (let [spots [nil nil nil
                     :x  :o  nil
                     nil :o  :x]]
          (should= 1 (choose-best-spot spots :x :o)))))))
