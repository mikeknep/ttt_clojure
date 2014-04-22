(ns tictactoe.console-io
  (:require [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]
            [tictactoe.rules :refer [valid-spot? valid-board-size? valid-player-type? winner-present? get-winner]]))

(defn get-move [spots & args]
  (loop [prompt "Where do you want to go next?"]
    (println prompt)
    (let [input (read-line)]
      (if (valid-spot? spots (read-string input))
        (read-string input)
        (recur "That is not a valid spot. Please input the index of the spot you want to play next as an integer.")))))

(defn get-board-size []
  (loop [prompt "What size board do you want to play on? (3 or 4)"]
    (println prompt)
    (let [input (read-line)]
      (if (valid-board-size? input)
        (read-string input)
        (recur "That is not a valid size. Please input the number of rows you want on the board (3 or 4) as an integer.")))))

(defn get-player-decision-maker []
  (loop [prompt "What kind of player is this? ('human', 'easy computer' or 'hard computer')"]
    (println prompt)
    (let [input (read-line)]
      (if (valid-player-type? input)
        (case input
          "human"         get-move
          "easy computer" choose-random-spot
          "hard computer" choose-best-spot)
        (recur "That is not a valid player type. Please select 'human', 'easy computer', or 'hard computer'.")))))

(defn display-spot [spot]
  (case spot
        :x    "X"
        :o    "O"
        nil   " "))

(defn border [spot-index board-size]
  (if (== 0 (mod (+ 1 spot-index) board-size))
    "\n"
    "|"))

(defn format-board [spots]
  (let [board-size (Math/sqrt (count spots))]
    (loop [spots          spots
           board-string   ""
           spot-index     0]
      (if (empty? spots)
        board-string
        (recur (rest spots) (str board-string (display-spot (first spots)) (border spot-index board-size)) (inc spot-index))))))

(defn display-board [spots]
  (println (str "\n\n" (format-board spots) "\n\n")))

(defn declare-whose-turn [formatted-spot]
  (println (str formatted-spot "'s turn")))

(defn declare-draw []
  (println "Cat's game!"))

(defn declare-winner [winning-token]
  (println (str (display-spot winning-token) " wins!")))

(defn declare-result [spots]
  (if (winner-present? spots)
    (declare-winner (get-winner spots))
    (declare-draw)))
