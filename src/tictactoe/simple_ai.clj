(ns tictactoe.simple_ai)

(defn choose-spot [board]
  (first (keep-indexed #(if (= nil %2) %1) (board :spots))))
