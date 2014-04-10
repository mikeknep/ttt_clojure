(ns tictactoe.rules)

(defn valid-spot? [spots index]
  (= nil (spots index)))

(defn all-spots-taken? [spots]
  (= 0 (count (filter (fn [spot] (= spot nil)) spots))))

(defn winner-in-collection? [tokens]
  (if (and (= 1 (count (distinct tokens))) (complement (nil? (first (distinct tokens)))))
    (first (distinct tokens))))
