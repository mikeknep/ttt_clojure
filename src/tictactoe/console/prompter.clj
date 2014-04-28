(ns tictactoe.console.prompter)

(defn prompt [template]
  (loop [prompt (get template :first-prompt)]
    (println prompt)
    (let [input (read-line)]
      (if ((get template :validity-checker) input)
        ((get template :followup-fn) input)
        (recur (get template :second-prompt))))))

(defn print-with-padding [text]
  (println (str "\n\n" text "\n\n")))
