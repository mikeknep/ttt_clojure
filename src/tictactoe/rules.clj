(ns tictactoe.rules
  (:require [tictactoe.board-shaper :refer [all-winning-indexes]]
            [tictactoe.board :refer [values-at-indexes]]))

(defn valid-spot? [spots index]
  (= nil (spots index)))

(defn all-spots-taken? [spots]
  (= 0 (count (filter (fn [spot] (= spot nil)) spots))))

(defn winner-in-collection? [tokens]
  (if (and (= 1 (count (distinct tokens))) (complement (nil? (first (distinct tokens)))))
    (first (distinct tokens))))

(defn winner-on-board? [board]
  (loop [paths  (all-winning-indexes (get board :size))
         spots  (get board :spots)]
    (if (winner-in-collection? (values-at-indexes (first paths) spots))
      (winner-in-collection? (values-at-indexes (first paths) spots))
      (if (= 0 (count paths))
        nil
        (recur (rest paths) spots)))))
