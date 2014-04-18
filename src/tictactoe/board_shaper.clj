(ns tictactoe.board-shaper)

(defn row-indexes [size]
  (loop [all-rows   []
         counter    0]
    (if (= counter size)
      all-rows
      (recur (conj all-rows (take size (iterate inc (* size counter)))) (inc counter)))))

(defn column-indexes [size]
  (loop [all-columns  []
         counter      0]
    (if (= counter size)
      all-columns
      (recur (conj all-columns (take size (iterate (partial + size) counter)))(inc counter)))))

(defn diagonal-indexes [size]
  [
   (take size (iterate (partial + (+ 1 size)) 0))
   (take size (iterate (partial + (- size 1)) (- size 1)))
   ])

(defn all-winning-indexes [size]
  (reduce into [(row-indexes size) (column-indexes size) (diagonal-indexes size)]))
