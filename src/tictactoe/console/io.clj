(ns tictactoe.console.io)

(defn prompt [template]
  (loop [prompt (template :first-prompt)]
    (println prompt)
    (let [input (read-line)]
      (if ((template :validity-checker) input)
        ((template :followup-fn) input)
        (recur (template :second-prompt))))))

(defn display [string]
  (println string))
