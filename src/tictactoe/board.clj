(ns tictactoe.board)

(defn create-board [size]
  {:size    size
   :spots   (repeat (* size size) nil)})

(defn values-at-indexes [indexes spots]
  (loop [indexes  indexes
         spots    spots
         tokens   []]
    (if (empty? indexes)
      tokens
      (recur (rest indexes) spots (conj tokens (nth spots (first indexes)))))))
