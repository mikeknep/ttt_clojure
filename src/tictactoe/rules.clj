(ns tictactoe.rules
  (:require [tictactoe.board-shaper :refer [all-winning-indexes]]
            [tictactoe.board :refer [values-at-indexes]]))

(defn valid-spot? [spots index]
  (= nil (spots index)))

(defn current-token [spots]
  (if (= (count (filter #{:x} spots)) (count (filter #{:o} spots)))
    :x
    :o))

(defn all-spots-taken? [spots]
  (= 0 (count (filter (fn [spot] (= spot nil)) spots))))

(defn winner-in-collection? [tokens]
  (and (= 1 (count (distinct tokens))) (not= nil (first (distinct tokens)))))

(defn winner-on-board? [board]
  (loop [paths  (all-winning-indexes (get board :size))
         spots  (get board :spots)]
    (if (winner-in-collection? (values-at-indexes (first paths) spots))
      true
      (if (empty? paths)
        false
        (recur (rest paths) spots)))))

(defn game-over? [board]
  (or (all-spots-taken? (get board :spots)) (winner-on-board? board)))

(defn get-winner [board]
  (loop [paths  (all-winning-indexes (get board :size))
         spots  (get board :spots)]
    (if (winner-in-collection? (values-at-indexes (first paths) spots))
      (first (distinct (values-at-indexes (first paths) spots)))
      (recur (rest paths) spots))))
