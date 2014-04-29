(ns tictactoe.parser)

(defn parse [file line-number]
  (nth (with-open [rdr (clojure.java.io/reader file)] (reduce conj [] (line-seq rdr))) line-number))
