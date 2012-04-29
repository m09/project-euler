import Data.List

main = print.head.dropWhile ((< 500).nDivisors).scanl1 (+) $ [1..]

--http://en.wikipedia.org/wiki/Divisor_function#Properties
nDivisors :: Integer -> Integer
nDivisors = product.map ((+ 1).genericLength).group.sort.primeFactors

primeFactors :: Integer -> [Integer]
primeFactors x = go x primes []
  where go 1 _ acc = acc
        go x l@(p:ps) acc | x `mod` p == 0 = go (x `div` p) l (p:acc)
                          | otherwise = go x ps acc

primes :: [Integer]
primes = 2 : 3 : 5 : filter isPrime [7, 9..]
  where sqrtInt = ceiling.sqrt.fromIntegral
        isPrime n = all (\m -> n `mod` m /= 0).takeWhile (<= sqrtInt n) $ primes
