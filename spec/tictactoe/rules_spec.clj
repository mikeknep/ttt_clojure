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

  (it "returns the token from a winning collection"
    (let [tokens  [:x :x :x]]
      (should= :x (winner-in-collection? tokens))))

  (it "returns nil if a collection has multiple tokens"
    (let [tokens [:x :o :x]]
      (should= nil (winner-in-collection? tokens))))

  (it "returns nil if a collection has all empty spaces"
    (let [tokens [nil nil nil]]
      (should= nil (winner-in-collection? tokens)))))
