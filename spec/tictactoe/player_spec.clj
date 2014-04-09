(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]))

(describe "a player"
  (it "is created with a token"
    (should= "X" ((create-player "X") :token))))
