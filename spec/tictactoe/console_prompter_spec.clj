(ns tictactoe.console-prompter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-prompter :refer :all]))

(describe "console-prompter"
  (around [it]
    (with-out-str (it)))

  (with mock-template {:first-prompt "Hello"
                       :second-prompt "World"
                       :validity-checker #(= "foo" %)
                       :followup-fn count})

  (it "prints the first prompt"
    (should-contain "Hello\n"
      (with-out-str
        (with-in-str "foo"
          (prompt @mock-template)))))

  (it "performs a followup function on a valid input"
    (should= 3
      (with-in-str "foo"
        (prompt @mock-template))))

  (it "prints the second prompt when the input is invalid"
    (should-contain "World\n"
      (with-out-str
        (with-in-str "bar\nfoo"
          (prompt @mock-template))))))
