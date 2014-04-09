(ns tictactoe.board)

(defn spaces [size tokens]
  (loop [tokens   tokens
         spots    []
         counter  0]
    (if (= (* size size) counter)
      spots
      (if (= counter (first (sort (keys tokens))))
        (recur (dissoc tokens counter) (conj spots (tokens counter)) (inc counter))
        (recur tokens (conj spots nil) (inc counter))))))
