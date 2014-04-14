(ns tictactoe.game-runner-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game-runner :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.console-io :refer [get-move]]))

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
      (should-contain "!\n" (with-out-str (play-game game)))))

  (it "plays a whole game with an easy computer and a human"
    (let [game {:board {:size 3 :spots [nil nil nil nil nil nil nil nil nil]}
                :player-1 {:token :x :decision-maker get-move}
                :player-2 {:token :o :decision-maker choose-random-spot}}]
      (should-contain "!\n" (with-out-str (with-in-str "0\n1\n2\n3\n4\n5\n6\n7\n8\n" (play-game game))))))

  (it "plays a whole game with two human players"
    (let [game {:board {:size 3 :spots [nil nil nil nil nil nil nil nil nil]}
                :player-1 {:token :x :decision-maker get-move}
                :player-2 {:token :o :decision-maker get-move}}]
      (should-contain "O wins!\n" (with-out-str (with-in-str "3\n2\n8\n1\n6\n0\n" (play-game game)))))))
