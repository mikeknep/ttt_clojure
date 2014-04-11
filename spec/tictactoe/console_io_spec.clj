(ns tictactoe.console-io-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-io :refer :all]))

(describe "console io"

  (it "asks for a move and returns an integer"
    (should= 4 
      (with-in-str "4"
        (get-move)))))
