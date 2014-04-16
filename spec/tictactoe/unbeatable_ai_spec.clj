(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "unbeatable ai"
  (context "scoring a board"
    (it "scores a board with a cat's game zero 0"
      (let [board {:size 3 :spots [:x :o :x :x :o :x :o :x :o]}]
        (should= 0 (score-board board 0))))

    (it "scores a board with a winner at zero depth level +1.0"
      (let [board {:size 3 :spots [:x :x :x nil nil nil nil nil nil]}]
        (should= 1.0 (score-board board 0))))

    (it "scores a board with a winner at one depth level -1.0"
      (let [board {:size 3 :spots [:x :x :x nil nil nil nil nil nil]}]
        (should= -1.0 (score-board board 1))))

    (it "scores a board with a winner at two depth levels +1.0"
      (let [board {:size 3 :spots [:x :x :x nil nil nil nil nil nil]}]
        (should= 1.0 (score-board board 2))))))
