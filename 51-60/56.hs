import Data.Char

main = print . maximum $ [ digitsSum (a ^ b) | a <- [1..100], b <- [1..100] ]

digitsSum :: Integer -> Int
digitsSum = sum . (map digitToInt) . show
