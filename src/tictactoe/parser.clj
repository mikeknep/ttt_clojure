(ns tictactoe.parser)

(defn parse [file line-number]
  (nth (with-open [rdr (clojure.java.io/reader file)] (reduce conj [] (line-seq rdr))) line-number))

(def current-player-text
  (parse "translations/english.txt" 0))

(def draw-text
  (parse "translations/english.txt" 1))

(def winner-text
  (parse "translations/english.txt" 2))

(def player-type-prompt-1
  (parse "translations/english.txt" 3))

(def player-type-prompt-2
  (parse "translations/english.txt" 4))

(def player-token-prompt-1
  (parse "translations/english.txt" 5))

(def player-token-prompt-2
  (parse "translations/english.txt" 6))

(def play-again-prompt-1
  (parse "translations/english.txt" 7))

(def play-again-prompt-2
  (parse "translations/english.txt" 8))
