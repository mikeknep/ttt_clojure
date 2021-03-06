(ns tictactoe.console.board-presenter
  (:require [tictactoe.board :refer [board-length]]))

(defn- border [spot-index board-length]
  (if (== 0 (mod (+ 1 spot-index) board-length))
    "\n"
    "|"))

(defn- present-spot [board index display-type]
  (case display-type
    :traditional (if (nil? (board index)) " " (board index))
    :legend      (if (nil? (board index)) index " ")))

(defn present-board [board display-type]
  (loop [string ""
         index  0]
    (if (= (count board) index)
      string
      (recur (str string (present-spot board index display-type) (border index (board-length board)))
             (inc index)))))
