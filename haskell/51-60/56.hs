{-# OPTIONS_GHC -Wall #-}

import Data.Char

main :: IO ()
main = print
     . maximum
     $ [ digitsSum (a ^ b) | a <- [1..100] :: [Integer]
                           , b <- [1..100] :: [Integer] ]

digitsSum :: Integer -> Int
digitsSum = sum . map digitToInt . show
