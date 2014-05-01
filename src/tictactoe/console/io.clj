(ns tictactoe.console.io)

(defn prompt [template]
  (loop [prompt (template :first-prompt)]
    (println prompt)
    (let [input (read-line)]
      (if ((template :validity-checker) input)
        ((template :followup-fn) input)
        (recur (template :second-prompt))))))

(defn print-with-padding [text]
  (println (str "\n\n" text "\n\n")))
