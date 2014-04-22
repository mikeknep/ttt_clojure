(ns tictactoe.paths)

(defn row-indexes [length]
  (loop [all-rows   []
         counter    0]
    (if (== counter length)
      all-rows
      (recur (conj all-rows (take length (iterate inc (* length counter)))) (inc counter)))))

(defn column-indexes [length]
  (loop [all-columns  []
         counter      0]
    (if (== counter length)
      all-columns
      (recur (conj all-columns (take length (iterate (partial + length) counter)))(inc counter)))))

(defn diagonal-indexes [length]
  [
   (take length (iterate (partial + (+ 1 length)) 0))
   (take length (iterate (partial + (- length 1)) (- length 1)))
   ])

(defn all-winning-indexes [length]
  (reduce into [(row-indexes length) (column-indexes length) (diagonal-indexes length)]))
