{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = print
     . length
     $ [ nCr | n <- [1 .. 100]
             , r <- [1 .. n]
             , let nCr = fact n `div` (fact r * fact (n - r))
             , nCr > 1000000 ]

fact :: Integer -> Integer
fact n = product [1 .. n]
