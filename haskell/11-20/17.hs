{-# OPTIONS_GHC -Wall #-}

main :: IO()
main = print total

units :: Int
units = sum . map length
        $ [ "one"
          , "two"
          , "three"
          , "four"
          , "five"
          , "six"
          , "seven"
          , "eight"
          , "nine" ]

teens :: Int
teens = sum . map length
        $ [ "ten"
          , "eleven"
          , "twelve"
          , "thirteen"
          , "fourteen"
          , "fifteen"
          , "sixteen"
          , "seventeen"
          , "eighteen"
          , "nineteen" ]

tens :: Int
tens = sum . map length
        $ [ "twenty"
          , "thirty"
          , "forty"
          , "fifty"
          , "sixty"
          , "seventy"
          , "eighty"
          , "ninety" ]

ninety_nine :: Int
ninety_nine = units * 9
              + teens
              + tens * 10

total :: Int
total = ninety_nine * 10
        + units * 100
        + length "hundred" * 900
        + length "and" * 891
        + length "onethousand"
