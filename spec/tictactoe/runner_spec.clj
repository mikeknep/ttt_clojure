(ns tictactoe.runner-spec
  (:require [speclj.core :refer :all]
            [tictactoe.runner :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.console-io :refer [get-move]]))

(describe "runner"
  (around [it]
    (with-out-str (it)))

  (with easy-x    {:token :x :decision-maker choose-random-spot})

  (with easy-o    {:token :o :decision-maker choose-random-spot})

  (with win-coll  [:x  :x  :x
                   :o  :o  nil
                   nil nil nil])

  (with draw-coll [:x :o :x
                   :x :o :x
                   :o :x :o])

  (with win-for-O [:o  :x  :x
                   nil :x  :x
                   :o  nil :o])

  (with unplayed  [nil nil nil
                   nil nil nil
                   nil nil nil])

  (it "ends a game that is a draw"
    (should-contain "Cat's game!\n"
      (with-out-str (play @draw-coll @easy-x @easy-o))))

  (it "ends a game that has a winner"
    (should-contain "X wins!\n"
      (with-out-str (play @win-coll @easy-x @easy-o))))

  (it "plays O's turn and declares O the winner"
    (should-contain "O wins!\n"
      (with-out-str (play @win-for-O @easy-x @easy-o))))

  (it "plays a whole game with two easy computer players"
    (should-contain "!\n"
      (with-out-str (play @unplayed @easy-x @easy-o)))))
