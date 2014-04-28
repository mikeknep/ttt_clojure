(ns tictactoe.console.text-presenter
  (:require [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [winner-present? get-winner]]))

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

(def get-player-type
  {:first-prompt "What type of player is this? ('human' 'easy computer' or 'hard computer')"
   :second-prompt "That is not a valid player type. Please input 'human' 'easy computer' or 'hard computer'."
   :validity-checker #(contains? #{"human" "easy computer" "hard computer"} %)
   :followup-fn eval})

(def get-player-token
  {:first-prompt "What is this player's token?"
   :second-prompt "That is not a valid token. Please select a unique, one-character token."
   :validity-checker #(= 1 (count %))
   :followup-fn eval})

(def get-play-again
  {:first-prompt "Play again? (y/n)"
   :second-prompt "Do you want to play again or not? (y/n)"
   :validity-checker #(contains? #{"y" "n"} %)
   :followup-fn #(= "y" %)})
