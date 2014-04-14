(ns tictactoe.game-runner-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game-runner :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]))

(describe "game-runner"
  (around [it]
    (with-out-str (it)))

  (it "ends a game that is a draw"
    (let [game  {:board     {:size 3 :spots [:x :o :x :x :o :x :o :x :o]}
                 :player-1  {:token :x :decision-maker choose-random-spot}
                 :player-2  {:token :o :decision-maker choose-random-spot}}]
      (should= "Cat's game!\n"
        (with-out-str (play-game game)))))

  (it "ends a game that has a winner"
    (let [game  {:board   {:size 3 :spots [:x :x :x :o :o nil nil nil nil]}
                 :player-1  {:token :x :decision-maker choose-random-spot}
                 :player-2  {:token :o :decision-maker choose-random-spot}}]
      (should= "X wins!\n"
        (with-out-str (play-game game)))))

  (it "plays O's turn and declares O the winner"
    (let [game  {:board {:size 3 :spots [:o :x :x nil :x :x :o nil :o]}
                 :player-1 {:token :x :decision-maker choose-random-spot}
                 :player-2 {:token :o :decision-maker choose-random-spot}}]
      (should= "O wins!\n"
        (with-out-str (play-game game)))))

  (it "plays a whole game with two easy computer players"
    (let [game  {:board {:size 3 :spots [nil nil nil nil nil nil nil nil nil]}
                 :player-1 {:token :x :decision-maker choose-random-spot}
                 :player-2 {:token :o :decision-maker choose-random-spot}}]
      (should-contain "!\n" (with-out-str (play-game game))))))
