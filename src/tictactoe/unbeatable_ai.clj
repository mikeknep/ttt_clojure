(ns tictactoe.unbeatable-ai
  (:require [tictactoe.rules :refer [first-move? available-spots game-over? all-spots-taken? winner-present? valid-spot?]]
            [tictactoe.gameplay :refer [take-turn]]))

(defn simple-score [board]
  (if (winner-present? board)
    1.0
    0.0))

(defn score-with-depth [board depth]
  (if (zero? (simple-score board))
    0
    (* (/ (simple-score board) (double depth)) (reduce * (repeat depth -1)))))

(defn create-altered-board [board index token]
  (take-turn board index token))

(defn child-boards [board token]
  (map create-altered-board (repeat board) (available-spots board) (repeat token)))

(defn min-or-max [depth]
  (if (even? depth) min max))

(defn choose-middle-spot [board]
  4)


(defn minimax [board current-token opponent-token depth]
  (if (game-over? board)
    (score-with-depth board depth)
    (if (= depth 5)
      0
      (apply (min-or-max depth) (map minimax (child-boards board opponent-token) (repeat opponent-token) (repeat current-token) (repeat (+ 1 depth)))))))


(defn choose-best-spot [board current-token opponent-token]
  (loop [best-score   (/ -1.0 0)
         best-spot    -1
         open-spots   (available-spots board)]
    (if (empty? open-spots)
      best-spot
      (if (first-move? board)
        (choose-middle-spot board)
        (let [this-spot       (first open-spots)
              altered-board   (create-altered-board board this-spot current-token)
              this-score      (minimax altered-board current-token opponent-token 0)]
          (recur
            (if (> this-score best-score) this-score best-score)
            (if (> this-score best-score) this-spot best-spot)
            (rest open-spots)))))))
