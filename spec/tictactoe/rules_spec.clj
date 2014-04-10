(ns tictactoe.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe.rules :refer :all]))

(describe "rules"
  (it "knows a spot is not valid if it has been played"
    (let [spots [:x nil nil nil]]
      (should= false (valid-spot? spots 0))))


  (context "checking for a draw"
    (it "recognizes all spots as being taken"
      (let [spots [:x :o :x :o]]
        (should= true (all-spots-taken? spots))))

    (it "recognizes spots still open to be played"
      (let [spots [:x :o :z nil]]
        (should= false (all-spots-taken? spots)))))


  (context "checking a single path for a winner"
    (it "returns the token from a winning collection"
      (let [tokens  [:x :x :x]]
        (should= :x (winner-in-collection? tokens))))

    (it "returns nil if a collection has multiple tokens"
      (let [tokens [:x :o :x]]
        (should= nil (winner-in-collection? tokens))))

    (it "returns nil if a collection has all empty spaces"
      (let [tokens [nil nil nil]]
        (should= nil (winner-in-collection? tokens)))))


  (context "checking the whole board for a winner"
    (it "returns the winning token from a board with a winner"
      (let [board {:size 3 :spots [:x nil nil :x nil nil :x nil nil]}]
        (should= :x (winner-on-board? board))))

    (it "returns nil for a board with no winner"
      (let [board {:size 3 :spots (repeat 9 nil)}]
        (should= nil (winner-on-board? board))))))
