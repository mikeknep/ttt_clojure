(ns tictactoe.console.text-presenter
  (:require [tictactoe.board :refer [board-length]]
            [tictactoe.rules :refer [winner-present? get-winner]]))

(defn parse [file line-number]
  (nth (with-open [rdr (clojure.java.io/reader file)] (reduce conj [] (line-seq rdr))) line-number))

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
  {:first-prompt "What type of player is this? (1: human, 2: easy computer, 3: hard computer)"
   :second-prompt "That is not a valid player type. Please input 1 for human, 2 for easy computer, or 3 for hard computer."
   :validity-checker #(contains? #{"1" "2" "3"} %)
   :followup-fn #(case %
                       "1" "human"
                       "2" "easy computer"
                       "3" "hard computer")})

(def get-player-token
  {:first-prompt "What is this player's token?"
   :second-prompt "That is not a valid token. Please select a unique, one-character token."
   :validity-checker #(= 1 (count %))
   :followup-fn eval})

(def get-play-again
  {:first-prompt "Play again? (1: yes, 2: no)"
   :second-prompt "Do you want to play again or not? (Input 1 to play again. Input 2 to quit.)"
   :validity-checker #(contains? #{"1" "2"} %)
   :followup-fn #(= "1" %)})
