(ns tictactoe.parser-spec
  (:require [speclj.core :refer :all]
            [tictactoe.parser :refer :all]))

(describe "parser"
  (with mock-file "mock.txt")

  (it "returns a line from a file"
    (should= "Hello world!" (parse @mock-file 0)))

  (it "only returns a single line"
    (should= "This is a separate line." (parse @mock-file 1))))
