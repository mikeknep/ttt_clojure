# Tic Tac Toe in Clojure

**8th Light's favorite game in 8th Light's favorite language.**

To play a game, run `lein trampoline run`.

A player can be a human, easy computer (which simply chooses a random open spot on the board), or hard computer (which uses the MiniMax algorithm to choose the best open spot on the board).

To chose a spot on the board, input its index number--the board is zero-indexed and ascends left-to-right, top-to-bottom (like reading):

0 | 1 | 2

3 | 4 | 5

6 | 7 | 8
