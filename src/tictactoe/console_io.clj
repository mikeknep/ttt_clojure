(ns tictactoe.console-io
  (:require [tictactoe.simple-ai :refer [choose-random-spot]]
            [tictactoe.unbeatable-ai :refer [choose-best-spot]]
            [tictactoe.rules :refer [valid-spot? valid-board-length? valid-player-type? valid-token?]]
            [tictactoe.console-presenter :refer [format-legend]]))


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

(defn get-player-token []
  (loop [prompt "What is this player's token?"]
    (println prompt)
    (let [input (read-line)]
      (if (valid-token? input)
        input
        (recur "That token is not valid. Please select a unique, one-character token.")))))

(defn play-again? []
  (loop [prompt "Play again? (y/n)"]
    (println prompt)
    (case (read-line)
          "y"   true
          "n"   false
          (recur "Do you want to play again or not? (y/n)"))))
