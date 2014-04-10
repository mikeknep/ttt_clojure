(ns tictactoe.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe.rules :refer :all]))

(describe "rules"
  (it "knows a spot is not valid if it has been played"
    (let [spots [:x nil nil nil]]
      (should= false (valid-spot? spots 0))))

  (it "recognizes all spots as being taken"
    (let [spots [:x :o :x :o]]
      (should= true (all-spots-taken? spots))))

  (it "recognizes spots still open to be played"
    (let [spots [:x :o :z nil]]
      (should= false (all-spots-taken? spots))))

  (it "determines the indexes of rows on a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8]] (row-indexes 3)))

  (it "determines the indexes of columns on a 3x3 board"
    (should= [[0 3 6] [1 4 7] [2 5 8]] (column-indexes 3)))

  (it "determines the indexes of diagonals on a 3x3 board"
    (should= [[0 4 8] [2 4 6]] (diagonal-indexes 3)))

  (it "returns the indexes of all winning paths on a 3x3 board"
    (should= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]] (all-winning-indexes 3)))

  (it "returns the tokens at provided indexes"
    (let [indexes [0 2 4]
          spots   [:x nil :o nil nil]]
      (should= [:x :o nil] (values-at-indexes indexes spots))))

  (it "returns the token from a winning row"
    (let [tokens  [:x :x :x]]
      (should= :x (winner-in-collection? tokens))))

  (it "returns nil if a row has multiple tokens"
    (let [tokens [:x :o :x]]
      (should= nil (winner-in-collection? tokens))))

  (it "returns nil if a row has all empty spaces"
    (let [tokens [nil nil nil]]
      (should= nil (winner-in-collection? tokens)))))
