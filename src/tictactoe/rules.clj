(ns tictactoe.rules
  (:require [tictactoe.paths :refer [all-winning-indexes]]
            [tictactoe.board :refer [board-length values-at-indexes]]))

(defn valid-board-length? [chosen-length-input]
  (contains? #{"3" "4"} chosen-length-input))

(defn valid-player-type? [chosen-player-type-input]
  (contains? #{"human" "easy computer" "hard computer"} chosen-player-type-input))

(defn valid-token?
  ([token]
    (= 1 (count token)))
  ([token existing-token]
    (and (valid-token? token) (not= token existing-token))))

(defn valid-spot? [board index]
  (and (number? index) (= nil (get board index)) (>= index 0) (< index (count board))))

(defn available-spots [board]
  (keep-indexed #(if (nil? %2) %1) board))

(defn all-spots-taken? [board]
  (= 0 (count (filter (fn [spot] (= spot nil)) board))))

(defn winner-in-collection? [tokens]
  (and (= 1 (count (distinct tokens))) (not= nil (first (distinct tokens)))))

(defn winner-present? [board]
  (loop [paths  (all-winning-indexes (board-length board))]
    (if (winner-in-collection? (values-at-indexes (first paths) board))
      true
      (if (empty? paths)
        false
        (recur (rest paths))))))

(defn game-over? [board]
  (or (all-spots-taken? board) (winner-present? board)))

(defn get-winner [board]
  (loop [paths  (all-winning-indexes (board-length board))]
    (if (winner-in-collection? (values-at-indexes (first paths) board))
      (first (distinct (values-at-indexes (first paths) board)))
      (recur (rest paths)))))
