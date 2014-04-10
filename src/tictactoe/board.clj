(ns tictactoe.board)

(defn create-board [length]
  {:length  length
   :spots   (repeat (* length length) nil)})

(defn values-at-indexes [indexes spots]
  (loop [indexes  indexes
         spots    spots
         tokens   []]
    (if (= 0 (count indexes))
      tokens
      (recur (rest indexes) spots (conj tokens (nth spots (first indexes)))))))
