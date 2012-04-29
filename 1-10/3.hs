main = print.maximum.primeFactors $ 600851475143

primeFactors :: Integer -> [Integer]
primeFactors x = go x primes []
  where go 1 _        acc                  = acc
        go x l@(p:ps) acc | x `mod` p == 0 = go (x `div` p) l  (p:acc)
                          | otherwise      = go x           ps acc

primes :: [Integer]
primes = 2 : 3 : 5 : filter isPrime [7, 9..]
  where sqrtInt = ceiling.sqrt.fromIntegral
        isPrime n = all (\m -> n `mod` m /= 0).takeWhile (<= sqrtInt n) $ primes

