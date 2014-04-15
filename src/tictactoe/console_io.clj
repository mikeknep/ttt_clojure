(ns tictactoe.console-io
  (:require [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.rules :refer [winner-on-board? get-winner]]))

(defn get-move [& args]
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
         board-string   ""
         spot-index     0]
    (if (empty? spots)
      board-string
      (recur (rest spots) (str board-string (display-spot (first spots)) (pipe-or-newline spot-index (get board :size))) (inc spot-index)))))

(defn display-board [board]
  (println (str "\n\n" (format-board board) "\n\n")))

(defn declare-whose-turn [formatted-spot]
  (println (str formatted-spot "'s turn")))

(defn declare-draw []
  (println "Cat's game!"))

(defn declare-winner [winning-token]
  (println (str (display-spot winning-token) " wins!")))

(defn declare-result [board]
  (if (winner-on-board? board)
    (declare-winner (get-winner board))
    (declare-draw)))
