(ns tictactoe.console-validator)

(defn valid-board-length? [chosen-length-input]
  (contains? #{"3" "4"} chosen-length-input))

(defn valid-player-type? [chosen-player-type-input]
  (contains? #{"human" "easy computer" "hard computer"} chosen-player-type-input))

(defn valid-token?
  ([token]
    (= 1 (count token)))
  ([token existing-token]
    (and (valid-token? token) (not= token existing-token))))

(defn valid-play-again-response? [response]
  (contains? #{"y" "n"} response))
