(ns tictactoe.human-turn-spec
  (:require [speclj.core :refer :all]
            [tictactoe.human-turn :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]))

(describe "human turn"
  (with board [nil "X" nil
               nil nil nil
               nil nil nil])

  (around [it]
    (with-out-str (it)))


  (it "waits for input and returns an integer"
    (should= 3
      (with-in-str "3"
        (get-move @board))))

  (it "does not complete until a valid spot is entered, and only returns the valid spot input"
    (should= 3
      (with-in-str "1\n3"
        (get-move @board)))))
