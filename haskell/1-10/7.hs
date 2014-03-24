{-# OPTIONS_GHC -Wall #-}

import Data.Numbers.Primes ( primes )

main :: IO ()
main = print $ (primes :: [Integer]) !! 10000
