(ns tictactoe.language
  (:require [tictactoe.parser :refer [parse]]))

(defn language []
  ((parse "config.txt") :language))

(defn language-source []
  (str "translations/" (clojure.string/lower-case (language)) ".txt"))
