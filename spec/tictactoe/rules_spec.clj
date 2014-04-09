(ns tictactoe.rules-spec
  (:require [speclj.core :refer :all]
            [tictactoe.rules :refer :all]))

(describe "rules"
  (it "knows a spot is not valid if it has been played"
    (let [spots [:x nil nil nil]]
      (should= false (valid-spot? spots 0)))))
