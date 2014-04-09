(ns tictactoe.board)

(defn spaces [tokens]
  (loop [tokens   tokens
         spots    []
         counter  0]
    (if (= 9 counter)
      spots
      (if (= counter (first (sort (keys tokens))))
        (recur (dissoc tokens counter) (conj spots (tokens counter)) (inc counter))
        (recur tokens (conj spots nil) (inc counter))))))
