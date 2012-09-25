{-# OPTIONS_GHC -Wall #-}

import Data.Numbers.Primes ( primes )

main :: IO ()
main = print
     . sum
     . takeWhile (< 2000000)
     $ (primes :: [Integer])
