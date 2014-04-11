(ns tictactoe.simple-ai)

(defn choose-spot [spots]
  (first (keep-indexed #(if (= nil %2) %1) spots)))
