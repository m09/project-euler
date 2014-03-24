{-# OPTIONS_GHC -Wall #-}

import Data.Numbers.Primes ( primes )

main :: IO ()
main = print
     . maximum
     . primeFactors
     $ 600851475143

primeFactors :: Integer -> [Integer]
primeFactors x = go x primes []
  where go 1 _        acc                  = acc
        go n l@(p:ps) acc | n `mod` p == 0 = go (n `div` p) l  (p:acc)
                          | otherwise      = go n           ps acc
        go _ []       _                    = undefined
