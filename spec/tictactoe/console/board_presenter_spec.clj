(ns tictactoe.console.board-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console.board-presenter :refer :all]))

(describe "console.board-presenter"
  (with board ["X" "O" nil
               "X" "O" nil
               nil nil nil])

  (describe "borders between spots"
    (it "returns a pipe to separate spots in the same row"
      (should= "|" (border 0 3)))

    (it "returns a new line to separate the spot at the end of a row from the next row"
      (should= "\n" (border 2 3))))

  (describe "presenting an individual spot on the board"
    (context "for traditional display"
      (it "returns a token for displaying a played spot"
        (should= "X" (present-spot @board 0 :traditional)))

      (it "returns a blank space for displaying an unplayed spot"
        (should= " " (present-spot @board 8 :traditional))))

    (context "for a human-only legend to assist with console gameplay"
      (it "returns a blank space for displaying a played spot"
        (should= " " (present-spot @board 0 :legend)))

      (it "returns the index value for displaying an unplayed spot"
        (should= 8 (present-spot @board 8 :legend)))))

  (describe "presenting an entire board"
    (it "presents a board for traditional display"
      (should= "X|O| \nX|O| \n | | \n" (present-board @board :traditional)))

    (it "presents a board for a human player as a legend"
      (should= " | |2\n | |5\n6|7|8\n" (present-board @board :legend)))))
