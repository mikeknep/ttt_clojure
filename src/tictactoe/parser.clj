(ns tictactoe.parser
  (:require [tictactoe.language :refer [language-source]]))

(defn parse [file line-number]
  (nth (with-open [rdr (clojure.java.io/reader file)] (reduce conj [] (line-seq rdr))) line-number))

(def current-player-text
  (parse (language-source) 0))

(def draw-text
  (parse (language-source) 1))

(def winner-text
  (parse (language-source) 2))

(def player-type-prompt-1
  (parse (language-source) 3))

(def player-type-prompt-2
  (parse (language-source) 4))

(def player-token-prompt-1
  (parse (language-source) 5))

(def player-token-prompt-2
  (parse (language-source) 6))

(def play-again-prompt-1
  (parse (language-source) 7))

(def play-again-prompt-2
  (parse (language-source) 8))
