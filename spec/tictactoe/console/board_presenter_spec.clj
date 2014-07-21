(ns tictactoe.console.board-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console.board-presenter :refer :all]))

(describe "console.board-presenter"
  (with board ["X" "O" nil
               "X" "O" nil
               nil nil nil])

  (it "presents a board for traditional display"
    (should= "X|O| \nX|O| \n | | \n" (present-board @board :traditional)))

  (it "presents a board for a human player as a legend"
    (should= " | |2\n | |5\n6|7|8\n" (present-board @board :legend))))
