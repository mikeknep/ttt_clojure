(ns tictactoe.runner-spec
  (:require [speclj.core :refer :all]
            [tictactoe.runner :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.console-io :refer [get-move]]))

(describe "runner"
  (around [it]
    (with-out-str (it)))

  (with easy-x    {:token "X" :decision-maker choose-random-spot})

  (with easy-o    {:token "O" :decision-maker choose-random-spot})

  (with win-board  ["X" "X" "X"
                    "O" "O" nil
                    nil nil nil])

  (with draw-board ["X" "O" "X"
                    "X" "O" "X"
                    "O" "X" "O"])

  (with win-for-O ["O" "X" "X"
                   nil "X" "X"
                   "O" nil "O"])

  (with unplayed  [nil nil nil
                   nil nil nil
                   nil nil nil])

  (it "ends a game that is a draw"
    (should-contain "Cat's game!\n"
      (with-out-str
        (with-in-str "n"
          (play @draw-board @easy-x @easy-o)))))

  (it "ends a game that has a winner"
    (should-contain "X wins!\n"
      (with-out-str
        (with-in-str "n"
          (play @win-board @easy-x @easy-o)))))

  (it "plays O's turn and declares O the winner"
    (should-contain "O wins!\n"
      (with-out-str
        (with-in-str "n"
          (play @win-for-O @easy-o @easy-x)))))

  (it "plays a whole game with two easy computer players"
    (should-contain "!\n"
      (with-out-str
        (with-in-str "n"
          (play @unplayed @easy-x @easy-o))))))
