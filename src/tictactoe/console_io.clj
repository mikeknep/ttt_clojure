(ns tictactoe.console-io
  (:require [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]
            [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [valid-spot? valid-board-length? valid-player-type? winner-present? get-winner]]))

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


(defn get-move [board & args]
  (loop [prompt "Where do you want to go next?"]
    (println prompt)
    (println (format-legend board))
    (let [input (read-line)]
      (if (valid-spot? board (read-string input))
        (read-string input)
        (recur "That is not a valid spot. Please input the index of the spot you want to play next as an integer.")))))

(defn get-board-length []
  (loop [prompt "What board length do you want to play on? (3 or 4)"]
    (println prompt)
    (let [input (read-line)]
      (if (valid-board-length? input)
        (read-string input)
        (recur "That is not a valid length Please input the number of rows you want on the board (3 or 4) as an integer.")))))

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

(defn format-board [board]
  (let [length (board-length board)]
    (loop [board          board
           board-string   ""
           index          0]
      (if (empty? board)
        board-string
        (recur (rest board)
               (str board-string (display-spot (first board)) (border index length))
               (inc index))))))

(defn display-board [board]
  (println (str "\n\n" (format-board board) "\n\n")))

(defn declare-whose-turn [formatted-spot]
  (println (str formatted-spot "'s turn")))

(defn declare-draw []
  (println "Cat's game!"))

(defn declare-winner [winning-token]
  (println (str (display-spot winning-token) " wins!")))

(defn declare-result [board]
  (if (winner-present? board)
    (declare-winner (get-winner board))
    (declare-draw)))

(defn play-again? []
  (loop [prompt "Play again? (y/n)"]
    (println prompt)
    (case (read-line)
          "y"   true
          "n"   false
          (recur "Do you want to play again or not? (y/n)"))))
