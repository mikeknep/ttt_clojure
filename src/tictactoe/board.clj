(ns tictactoe.board)

(defn create-board [length]
  {:length  length
   :spots   (repeat (* length length) nil)})
