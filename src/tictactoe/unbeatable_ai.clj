(ns tictactoe.unbeatable-ai
  (:require [tictactoe.rules :refer [available-spots game-over? all-spots-taken? winner-on-board?]]
            [tictactoe.board :refer [update-board]]
            [tictactoe.gameplay :refer [take-turn]]))

(defn simple-board-score [board]
  (if (winner-on-board? board)
    1.0
    0.0))

(defn score-board-with-depth [board depth]
  (if (zero? (simple-board-score board))
    0
    (* (/ (simple-board-score board) (double depth)) (reduce * (repeat depth -1)))))

(defn update-best-score [this-score best-score]
  (if (> this-score best-score)
    this-score
    best-score))

(defn update-best-spot [this-spot this-score best-spot best-score]
  (if (> this-score best-score)
    this-spot
    best-spot))

(defn create-altered-board [board index token]
  (update-board (get board :size) (take-turn (get board :spots) index token)))

(defn child-boards [board token]
  (map create-altered-board (repeat board) (available-spots (get board :spots)) (repeat token)))

(defn min-or-max [depth]
  (if (even? depth)
    min
    max))


(defn minimax [board current-token opponent-token depth]
  (if (game-over? board)
    (score-board-with-depth board depth)
    (apply (min-or-max depth) (map minimax (child-boards board opponent-token) (repeat opponent-token) (repeat current-token) (repeat (+ 1 depth))))))


(defn choose-best-spot [board current-token opponent-token]
  (loop [best-score   (/ -1.0 0)
         best-spot    -1
         open-spots   (available-spots (get board :spots))]
    (if (empty? open-spots)
      best-spot
      (let [this-spot       (first open-spots)
            altered-board   (create-altered-board board this-spot current-token)
            this-score      (minimax altered-board current-token opponent-token 0)]
        (recur
          (update-best-score this-score best-score)
          (update-best-spot this-spot this-score best-spot best-score)
          (rest open-spots))))))
