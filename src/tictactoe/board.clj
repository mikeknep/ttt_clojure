(ns tictactoe.board)

(defn create-board [size]
  (loop [spots    []
         counter  0]
    (if (= counter (* size size))
      spots
      (recur (conj spots nil) (inc counter)))))

(defn values-at-indexes [indexes spots]
  (loop [indexes  indexes
         tokens   []]
    (if (empty? indexes)
      tokens
      (recur (rest indexes) (conj tokens (nth spots (first indexes)))))))
