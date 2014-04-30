(ns tictactoe.language-spec
  (:require [speclj.core :refer :all]
            [tictactoe.language :refer :all]))

(describe "language"
  (with-stubs)
  (it "returns the english text file when language is set to English"
    (with-redefs [language #(str "English")]
      (should= "translations/english.txt" (language-source))))

  (it "returns the spanish text file when language is set to Spanish"
    (with-redefs [language #(str "Spanish")]
      (should= "translations/spanish.txt" (language-source)))))
