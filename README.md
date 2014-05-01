# Tic Tac Toe in Clojure

**8th Light's favorite game in 8th Light's favorite language.**

To play a game, run `lein trampoline run`.

A player can be a human, easy computer (which simply chooses a random open spot on the board), or hard computer (which uses the MiniMax algorithm to choose the best open spot on the board).

To chose a spot on the board, input its index number--the board is zero-indexed and ascends left-to-right, top-to-bottom (like reading):

0 | 1 | 2

3 | 4 | 5

6 | 7 | 8


## Foreign Language Support

To play in a foreign language, follow these two steps:

1. The English game prompts can be found at `translations/english.txt`. Translate each line into the language of your choice, and save it as [language].txt in the same "translations" directory. (Example: `translations/french.txt`)

2. Change the language setting in the `config.txt` file (found at the project root directory) to the new language. The name should be the same as the translation file's filename (but can be upcased, ex: "French").
