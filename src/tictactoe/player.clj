(ns tictactoe.player)

(defn create-player [decision-maker token]
  {:token token
   :decision-maker decision-maker})
