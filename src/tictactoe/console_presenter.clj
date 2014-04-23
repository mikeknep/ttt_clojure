(ns tictactoe.console-presenter
  (:require [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [winner-present? get-winner]]))


(defn border [spot-index board-length]
  (if (== 0 (mod (+ 1 spot-index) board-length))
    "\n"
    "|"))


(defn format-legend [board]
  (let [length (board-length board)]
    (loop [board          board
           legend-string  ""
           index          0]
      (if (empty? board)
        legend-string
        (recur (rest board)
               (str legend-string (if (nil? (first board)) index " ") (border index length))
               (inc index))))))


(defn format-board [board]
  (let [length (board-length board)]
    (loop [board          board
           board-string   ""
           index          0]
      (if (empty? board)
        board-string
        (recur (rest board)
               (str board-string (if (nil? (first board)) " " (first board)) (border index length))
               (inc index))))))


(defn present-board [board]
  (println (str "\n\n" (format-board board) "\n\n")))


(defn present-current-player [token]
  (println (str token "'s turn")))


(defn present-draw []
  (println "Cat's game!"))


(defn present-winner [winning-token]
  (println (str winning-token " wins!")))


(defn present-result [board]
  (if (winner-present? board)
    (present-winner (get-winner board))
    (present-draw)))
