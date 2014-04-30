(ns tictactoe.console.text-presenter
  (:require [tictactoe.parser :refer :all]
            [tictactoe.rules :refer [winner-present? get-winner]]))

(defn present-current-player [token]
  (str current-player-text " " token))

(defn present-draw []
  draw-text)

(defn present-winner [token]
  (str winner-text " " token))

(defn present-result [board]
  (if (winner-present? board)
    (present-winner (get-winner board))
    (present-draw)))

(def get-player-type
  {:first-prompt player-type-prompt-1
   :second-prompt player-type-prompt-2
   :validity-checker #(contains? #{"1" "2" "3"} %)
   :followup-fn #(case %
                       "1" "human"
                       "2" "easy computer"
                       "3" "hard computer")})

(def get-player-token
  {:first-prompt player-token-prompt-1
   :second-prompt player-token-prompt-2
   :validity-checker #(= 1 (count %))
   :followup-fn eval})

(def get-play-again
  {:first-prompt play-again-prompt-1
   :second-prompt play-again-prompt-2
   :validity-checker #(contains? #{"1" "2"} %)
   :followup-fn #(= "1" %)})
