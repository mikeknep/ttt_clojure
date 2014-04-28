(ns tictactoe.console-validator)

(defn valid-player-type? [chosen-player-type-input]
  (contains? #{"human" "easy computer" "hard computer"} chosen-player-type-input))

(defn valid-token? [token]
  (= 1 (count token)))

(defn valid-play-again-response? [response]
  (contains? #{"y" "n"} response))
