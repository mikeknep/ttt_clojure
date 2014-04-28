(ns tictactoe.console-presenter
  (:require [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [winner-present? get-winner]]))


(defn border [spot-index board-length]
  (if (== 0 (mod (+ 1 spot-index) board-length))
    "\n"
    "|"))

(defn format-spot [board index display-type]
  (cond
    (= :traditional display-type) (if (nil? (board index)) " " (board index))
    (= :legend display-type)      (if (nil? (board index)) index " ")))

(defn format-board [board display-type]
  (loop [string ""
         index  0]
    (if (= (count board) index)
      string
      (recur (str string (format-spot board index display-type) (border index (board-length board)))
             (inc index)))))



(defn present-board [board display-type]
  (println (str "\n\n" (format-board board display-type) "\n\n")))


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
