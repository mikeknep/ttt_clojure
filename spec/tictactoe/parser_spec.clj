(ns tictactoe.parser-spec
  (:require [speclj.core :refer :all]
            [tictactoe.parser :refer :all]))

(describe "parser"
  (with mock-file "spec/mock.txt")

  (it "returns the full file if no line number is specified"
    (should= "Hello world!\nThis is a separate line.\n" (parse @mock-file)))

  (it "returns a line from a file"
    (should= "Hello world!" (parse @mock-file 0)))

  (it "only returns a single line"
    (should= "This is a separate line." (parse @mock-file 1))))
