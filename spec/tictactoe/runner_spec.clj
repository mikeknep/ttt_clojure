(ns tictactoe.runner-spec
  (:require [speclj.core :refer :all]
            [tictactoe.runner :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]))

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

  (with do-not-play-again "2")

  (it "ends a game that is a draw"
    (should-contain #"[[D|d]raw|[C|c]at's game]"
      (with-out-str
        (with-in-str @do-not-play-again
          (play {:board @draw-board :player-1 @easy-x :player-2 @easy-o})))))

  (it "ends a game that has a winner"
    (should-contain #"[W|w]in"
      (with-out-str
        (with-in-str @do-not-play-again
          (play {:board @win-board :player-1 @easy-x :player-2 @easy-o})))))

  (it "plays O's turn and declares O the winner"
    (should-contain #"[C|c]urrent player"
      (with-out-str
        (with-in-str @do-not-play-again
          (play {:board @win-for-O :player-1 @easy-o :player-2 @easy-x})))))

  (it "plays a whole game with two easy computer players"
    (should-contain #"[P|p]lay again"
      (with-out-str
        (with-in-str @do-not-play-again
          (play {:board @unplayed :player-1 @easy-x :player-2 @easy-o}))))))
