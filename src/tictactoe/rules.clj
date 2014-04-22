(ns tictactoe.rules
  (:require [tictactoe.board-shaper :refer [all-winning-indexes]]
            [tictactoe.board :refer [values-at-indexes]]))

(defn valid-board-size? [chosen-size-input]
  (contains? #{"3" "4"} chosen-size-input))

(defn valid-player-type? [chosen-player-type-input]
  (contains? #{"human" "easy computer" "hard computer"} chosen-player-type-input))

(defn valid-spot? [board index]
  (and (number? index) (= nil (get board index)) (>= index 0) (< index (count board))))

(defn available-spots [board]
  (keep-indexed #(if (nil? %2) %1) board))

(defn current-token [board]
  (if (= (count (filter #{:x} board)) (count (filter #{:o} board)))
    :x
    :o))

(defn opponent-token [board]
  (if (= (count (filter #{:x} board)) (count (filter #{:o} board)))
    :o
    :x))

(defn all-spots-taken? [board]
  (= 0 (count (filter (fn [spot] (= spot nil)) board))))

(defn winner-in-collection? [tokens]
  (and (= 1 (count (distinct tokens))) (not= nil (first (distinct tokens)))))

(defn winner-present? [board]
  (loop [paths  (all-winning-indexes (Math/sqrt (count board)))]
    (if (winner-in-collection? (values-at-indexes (first paths) board))
      true
      (if (empty? paths)
        false
        (recur (rest paths))))))

(defn game-over? [board]
  (or (all-spots-taken? board) (winner-present? board)))

(defn get-winner [board]
  (loop [paths  (all-winning-indexes (Math/sqrt (count board)))]
    (if (winner-in-collection? (values-at-indexes (first paths) board))
      (first (distinct (values-at-indexes (first paths) board)))
      (recur (rest paths)))))
