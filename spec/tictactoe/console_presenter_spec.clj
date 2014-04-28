(ns tictactoe.console-presenter-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-presenter :refer :all]))

(describe "console-presenter"

  (describe "presenting a board"
    (with board ["X" "O" nil
                 "X" "O" nil
                 nil nil nil])

    (describe "forming borders between spots"
      (it "returns a pipe to separate spots in the same row"
        (should= "|" (border 0 3)))

      (it "returns a new line to separate the spot at the end of a row from the next row"
        (should= "\n" (border 2 3))))

    (describe "formatting an individual spot on the board"
      (context "for traditional display"
        (it "returns a token for displaying a played spot"
          (should= "X" (format-spot @board 0 :traditional)))

        (it "returns a blank space for displaying an unplayed spot"
          (should= " " (format-spot @board 8 :traditional))))

      (context "for a human-only legend to assist with console gameplay"
        (it "returns a blank space for displaying a played spot"
          (should= " " (format-spot @board 0 :legend)))
        (it "returns the index value for displaying an unplayed spot"
          (should= 8 (format-spot @board 8 :legend)))))

    (describe "formatting an entire board"
      (it "formats a board for traditional display"
        (should= "X|O| \nX|O| \n | | \n" (format-board @board :traditional)))
      (it "formats a board for a human player as a legend"
        (should= " | |2\n | |5\n6|7|8\n" (format-board @board :legend)))))




  (context "presenting game conditions"
    (it "presents the current player"
      (should= "X's turn\n"
        (with-out-str (present-current-player "X"))))

    (it "presents a draw game result"
      (should= "Cat's game!\n"
        (with-out-str (present-draw))))

    (it "presents a winner"
      (should= "X wins!\n"
        (with-out-str (present-winner "X"))))

    (it "presents the correct game result"
      (let [board ["X" "X" "X"
                   nil nil nil
                   nil nil nil]]
        (should= "X wins!\n"
          (with-out-str (present-result board)))))))
