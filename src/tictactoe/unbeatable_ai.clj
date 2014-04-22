(ns tictactoe.unbeatable-ai
  (:require [tictactoe.rules :refer [available-spots game-over? all-spots-taken? winner-on-board?]]
            [tictactoe.board :refer [update-board]]
            [tictactoe.gameplay :refer [take-turn]]))

(defn simple-board-score [spots]
  (if (winner-on-board? spots)
    1.0
    0.0))

(defn score-board-with-depth [spots depth]
  (if (zero? (simple-board-score spots))
    0
    (* (/ (simple-board-score spots) (double depth)) (reduce * (repeat depth -1)))))

(defn update-best-score [this-score best-score]
  (if (> this-score best-score)
    this-score
    best-score))

(defn update-best-spot [this-spot this-score best-spot best-score]
  (if (> this-score best-score)
    this-spot
    best-spot))

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
    (score-board-with-depth spots depth)
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
          (update-best-score this-score best-score)
          (update-best-spot this-spot this-score best-spot best-score)
          (rest open-spots))))))
