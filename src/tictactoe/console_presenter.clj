(ns tictactoe.console-presenter
  (:require [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [winner-present? get-winner]]))


(defn border [spot-index board-length]
  (if (== 0 (mod (+ 1 spot-index) board-length))
    "\n"
    "|"))

(defn present-spot [board index display-type]
  (cond
    (= :traditional display-type) (if (nil? (board index)) " " (board index))
    (= :legend display-type)      (if (nil? (board index)) index " ")))

(defn present-board [board display-type]
  (loop [string ""
         index  0]
    (if (= (count board) index)
      string
      (recur (str string (present-spot board index display-type) (border index (board-length board)))
             (inc index)))))


(defn present-current-player [token]
  (str token "'s turn"))

(defn present-draw []
  "Cat's game!")

(defn present-winner [token]
  (str token " wins!"))

(defn present-result [board]
  (if (winner-present? board)
    (present-winner (get-winner board))
    (present-draw)))
