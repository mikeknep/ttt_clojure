(ns tictactoe.console-io-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-io :refer :all]
            [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]))

(describe "console io"
  (around [it]
    (with-out-str (it)))

  (it "asks for a move and returns an integer"
    (should= 3
      (with-in-str "3"
        (get-move [nil "X" nil nil])))))
