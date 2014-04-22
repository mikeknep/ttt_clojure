(ns tictactoe.board)

(defn create-board [length]
  (loop [spots    []
         counter  0]
    (if (= counter (* length length))
      spots
      (recur (conj spots nil) (inc counter)))))

(defn board-length [board]
  (int (Math/sqrt (count board))))

(defn values-at-indexes [indexes board]
  (loop [indexes  indexes
         tokens   []]
    (if (empty? indexes)
      tokens
      (recur (rest indexes) (conj tokens (nth board (first indexes)))))))
