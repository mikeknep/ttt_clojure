(ns tictactoe.console-io
  (:require [tictactoe.simple-ai :refer [choose-random-spot]]))

(defn get-move []
  (println "Where do you want to go next?")
  (read-string (read-line)))

(defn get-board-size []
  (println "What size board do you want to play on?")
  (read-string (read-line)))

(defn get-player-decision-maker []
  (println "What kind of player is this? ('human' or 'easy computer')")
  (case (read-line)
        "human"           get-move
        "easy computer"   choose-random-spot))

(defn display-spot [spot]
  (case spot
        :x    "X"
        :o    "O"
        nil   " "))

(defn pipe-or-newline [spot-index board-size]
  (if (= 0 (mod (+ 1 spot-index) board-size))
    "\n"
    "|"))

(defn format-board [board]
  (loop [spots          (get board :spots)
         size           (get board :size)
         board-string   ""
         spot-index     0]
    (if (empty? spots)
      board-string
      (recur (rest spots) size (str board-string (display-spot (first spots)) (pipe-or-newline spot-index size)) (inc spot-index)))))

(defn display-board [board]
  (println (format-board board)))

(defn declare-draw []
  (println "Cat's game!"))

(defn declare-winner [winning-token]
  (println (str (display-spot winning-token) " wins!")))
