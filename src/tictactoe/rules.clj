(ns tictactoe.rules)

(defn valid-spot? [spots index]
  (= nil (spots index)))
