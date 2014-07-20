(ns tictactoe.parser)

(defn parse [file]
  (load-string (slurp file)))
