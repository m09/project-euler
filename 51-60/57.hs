{-# OPTIONS_GHC -Wall #-}

import Data.Ratio

roots :: [Ratio Integer]
roots = iterate (\x -> 1 + 1 / (x + 1)) (1 + 1 % 2)

isNumLengthier :: Ratio Integer -> Bool
isNumLengthier r = l num > l den
  where
    num = numerator r
    den = denominator r
    l = length . show

main :: IO ()
main = print
       . length
       . filter isNumLengthier
       . take 1000
       $ roots