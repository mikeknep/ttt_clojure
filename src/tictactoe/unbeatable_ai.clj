(ns tictactoe.unbeatable-ai
  (:require [tictactoe.rules :refer [available-spots game-over? all-spots-taken? winner-present?]]
            [tictactoe.gameplay :refer [take-turn]]))

(defn simple-score [spots]
  (if (winner-present? spots)
    1.0
    0.0))

(defn score-with-depth [spots depth]
  (if (zero? (simple-score spots))
    0
    (* (/ (simple-score spots) (double depth)) (reduce * (repeat depth -1)))))

(defn create-altered-board [spots index token]
  (take-turn spots index token))

(defn child-boards [spots token]
  (map create-altered-board (repeat spots) (available-spots spots) (repeat token)))

(defn min-or-max [depth]
  (if (even? depth)
    min
    max))


(defn minimax [spots current-token opponent-token depth]
  (if (game-over? spots)
    (score-with-depth spots depth)
    (apply (min-or-max depth) (map minimax (child-boards spots opponent-token) (repeat opponent-token) (repeat current-token) (repeat (+ 1 depth))))))


(defn choose-best-spot [spots current-token opponent-token]
  (loop [best-score   (/ -1.0 0)
         best-spot    -1
         open-spots   (available-spots spots)]
    (if (empty? open-spots)
      best-spot
      (let [this-spot       (first open-spots)
            altered-board   (create-altered-board spots this-spot current-token)
            this-score      (minimax altered-board current-token opponent-token 0)]
        (recur
          (if (> this-score best-score) this-score best-score)
          (if (> this-score best-score) this-spot best-spot)
          (rest open-spots))))))
