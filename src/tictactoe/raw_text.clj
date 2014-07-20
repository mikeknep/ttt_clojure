(ns tictactoe.raw-text
  (:require [tictactoe.parser :refer [parse]]
            [tictactoe.language :refer [language-source]]))

(def current-player-text
  ((parse (language-source)) :current-player))

(def draw-text
  ((parse (language-source)) :draw))

(def winner-text
  ((parse (language-source)) :winner))

(def player-type-prompt-1
  ((parse (language-source)) :player-type-prompt-1))

(def player-type-prompt-2
  ((parse (language-source)) :player-type-prompt-2))

(def player-token-prompt-1
  ((parse (language-source)) :player-token-prompt-1))

(def player-token-prompt-2
  ((parse (language-source)) :player-token-prompt-2))

(def play-again-prompt-1
  ((parse (language-source)) :play-again-prompt-1))

(def play-again-prompt-2
  ((parse (language-source)) :play-again-prompt-2))
