{-# OPTIONS_GHC -Wall -i..:. #-}

import Data.List
import Data.Numbers.Primes (primes)

main :: IO ()
main = print
     . head
     . dropWhile ((< 500) . nDivisors)
     . scanl1 (+)
     $ [1..]

--http://en.wikipedia.org/wiki/Divisor_function#Properties
nDivisors :: Integer -> Integer
nDivisors = product
          . map ((+ 1) . genericLength)
          . group
          . sort
          . primeFactors

primeFactors :: Integer -> [Integer]
primeFactors x = go x primes []
  where go 1 _        acc                  = acc
        go n l@(p:ps) acc | n `mod` p == 0 = go (n `div` p) l (p:acc)
                          | otherwise      = go n ps acc
        go _ _        _                    = undefined
