(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]))

(describe "game"
  (around [it]
    (with-out-str (it)))
  
  (it "is created with a board and two players"
    (should= {:board {:size 3 :spots (repeat 9 nil)}
              :player-1 {:token :x :decision-maker choose-random-spot}
              :player-2 {:token :o :decision-maker choose-random-spot}}
      (with-in-str "3\neasy computer\neasy computer"
        (create-game)))))
