(ns tictactoe.player)

(defn create-player [token decision-maker]
  {:token token
   :decision-maker decision-maker})
