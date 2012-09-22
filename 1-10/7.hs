main :: IO ()
main = print $ primes !! 10000

primes :: [Integer]
primes = 2 : 3 : 5 : filter isPrime [7, 9..]
  where sqrtInt = ceiling
                . (sqrt :: Double -> Double)
                . fromIntegral
        isPrime n = all (\m -> n `mod` m /= 0)
                  . takeWhile (<= sqrtInt n)
                  $ primes

