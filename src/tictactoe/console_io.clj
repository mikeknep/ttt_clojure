(ns tictactoe.console-io)

(defn get-move []
  (println "Where do you want to go next?")
  (read-string (read-line)))

(defn get-board-size []
  (println "What size board do you want to play on?")
  (read-string (read-line)))
