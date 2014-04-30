(ns tictactoe.language)

(defn language []
  "English")

(defn language-source []
  (str "translations/" (clojure.string/lower-case (language)) ".txt"))
