(ns tictactoe.console-prompter
  (:require [tictactoe.console-validator :refer [valid-board-length? valid-player-type? valid-token? valid-play-again-response?]]
            [tictactoe.player :refer [set-player-decision-maker]]))

(defn get-board-length []
  {:first-prompt "What board length do you want to play on? (3 or 4)"
   :second-prompt "That is not a valid board length. Please input the number of rows you want on the board as an integer (3 or 4)."
   :validity-checker valid-board-length?
   :followup-fn read-string})

(defn get-player-type []
  {:first-prompt "What type of player is this? ('human' 'easy computer' or 'hard computer')"
   :second-prompt "That is not a valid player type. Please input 'human' 'easy computer' or 'hard computer'."
   :validity-checker valid-player-type?
   :followup-fn set-player-decision-maker})

(defn get-player-token []
  {:first-prompt "What is this player's token?"
   :second-prompt "That is not a valid token. Please select a unique, one-character token."
   :validity-checker valid-token?
   :followup-fn eval})

(defn get-play-again []
  {:first-prompt "Play again? (y/n)"
   :second-prompt "Do you want to play again or not? (y/n)"
   :validity-checker valid-play-again-response?
   :followup-fn #(= "y" %)})

(defn prompt [template]
  (loop [prompt (get template :first-prompt)]
    (println prompt)
    (let [input (read-line)]
      (if ((get template :validity-checker) input)
        ((get template :followup-fn) input)
        (recur (get template :second-prompt))))))
