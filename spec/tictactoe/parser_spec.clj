(ns tictactoe.parser-spec
  (:require [speclj.core :refer :all]
            [tictactoe.parser :refer :all]))

(describe "parser"
  (with mock-file "spec/mock.txt")

  (it "converts a file to a clojure data structure"
    (should= {:hello "world" :foo "bar"} (parse @mock-file))))
