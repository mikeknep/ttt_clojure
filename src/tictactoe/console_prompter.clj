(ns tictactoe.console-prompter
  (:require [tictactoe.console-validator :refer [valid-board-length? valid-player-type? valid-token? valid-play-again-response?]]
            [tictactoe.player :refer [set-player-decision-maker]]))

(def get-player-type
  {:first-prompt "What type of player is this? ('human' 'easy computer' or 'hard computer')"
   :second-prompt "That is not a valid player type. Please input 'human' 'easy computer' or 'hard computer'."
   :validity-checker valid-player-type?
   :followup-fn set-player-decision-maker})

(def get-player-token
  {:first-prompt "What is this player's token?"
   :second-prompt "That is not a valid token. Please select a unique, one-character token."
   :validity-checker valid-token?
   :followup-fn eval})

(def get-play-again
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
