{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = print
     . sum
     . takeWhile (< 2000000)
     $ primes

primes :: [Integer]
primes = 2 : 3 : 5 : filter isPrime [7, 9..]
  where sqrtInt = ceiling
                . (sqrt :: Double -> Double)
                . fromIntegral
        isPrime n = all (\m -> n `mod` m /= 0)
                  . takeWhile (<= sqrtInt n)
                  $ primes

