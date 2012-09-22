{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = print
     . product
     $ ([2, 3, 5, 7, 11, 13, 17, 19, 2, 2, 2, 3] :: [Integer])
-- a minima, contains all prime factors, then handle 16 and 9 and you just
-- handled all the rest
