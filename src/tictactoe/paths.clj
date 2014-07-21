(ns tictactoe.paths)

(defn- row-indexes [length]
  (partition length (range (* length length))))

(defn- column-indexes [length]
  (apply mapv vector (row-indexes length)))

(defn- diagonal-indexes [length]
  [(take length (iterate (partial + (+ 1 length)) 0))
   (take length (iterate (partial + (- length 1)) (- length 1)))])

(defn all-winning-indexes [length]
  (reduce into [(row-indexes length) (column-indexes length) (diagonal-indexes length)]))
