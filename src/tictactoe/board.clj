(ns tictactoe.board)

(defn create-board [length]
  (vec (repeat (* length length) nil)))

(defn board-length [board]
  (int (Math/sqrt (count board))))

(defn values-at-indexes [indexes board]
  (map nth (repeat board) indexes))
