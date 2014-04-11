(ns tictactoe.console-io)

(defn get-move []
  (println "Where do you want to go next?")
  (read-string (read-line)))
