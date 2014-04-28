(ns tictactoe.console-prompter)

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

(defn prompt [template]
  (loop [prompt (get template :first-prompt)]
    (println prompt)
    (let [input (read-line)]
      (if ((get template :validity-checker) input)
        ((get template :followup-fn) input)
        (recur (get template :second-prompt))))))

(defn print-with-padding [text]
  (println (str "\n\n" text "\n\n")))
