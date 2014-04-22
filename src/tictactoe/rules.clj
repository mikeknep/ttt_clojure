(ns tictactoe.rules
  (:require [tictactoe.board-shaper :refer [all-winning-indexes]]
            [tictactoe.board :refer [values-at-indexes]]))

(defn valid-board-size? [chosen-size-input]
  (contains? #{"3" "4"} chosen-size-input))

(defn valid-player-type? [chosen-player-type-input]
  (contains? #{"human" "easy computer" "hard computer"} chosen-player-type-input))

(defn valid-spot? [spots index]
  (= nil (get spots index)))

(defn available-spots [spots]
  (keep-indexed #(if (nil? %2) %1) spots))

(defn current-token [spots]
  (if (= (count (filter #{:x} spots)) (count (filter #{:o} spots)))
    :x
    :o))

(defn opponent-token [spots]
  (if (= (count (filter #{:x} spots)) (count (filter #{:o} spots)))
    :o
    :x))

(defn all-spots-taken? [spots]
  (= 0 (count (filter (fn [spot] (= spot nil)) spots))))

(defn winner-in-collection? [tokens]
  (and (= 1 (count (distinct tokens))) (not= nil (first (distinct tokens)))))

(defn winner-on-board? [board]
  (loop [paths  (all-winning-indexes (get board :size))]
    (if (winner-in-collection? (values-at-indexes (first paths) (get board :spots)))
      true
      (if (empty? paths)
        false
        (recur (rest paths))))))

(defn game-over? [board]
  (or (all-spots-taken? (get board :spots)) (winner-on-board? board)))

(defn get-winner [board]
  (loop [paths  (all-winning-indexes (get board :size))]
    (if (winner-in-collection? (values-at-indexes (first paths) (get board :spots)))
      (first (distinct (values-at-indexes (first paths) (get board :spots))))
      (recur (rest paths)))))
