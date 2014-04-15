(ns tictactoe.board)

(defn create-board [size]
  (loop [spots    []
         counter  0]
    (if (= counter (* size size))
      {:size size :spots spots}
      (recur (assoc spots counter nil) (inc counter)))))

(defn update-board [size spots]
  {:size    size
   :spots   spots})

(defn values-at-indexes [indexes spots]
  (loop [indexes  indexes
         spots    spots
         tokens   []]
    (if (empty? indexes)
      tokens
      (recur (rest indexes) spots (conj tokens (nth spots (first indexes)))))))
