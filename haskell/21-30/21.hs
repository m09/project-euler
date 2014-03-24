{-# OPTIONS_GHC -Wall #-}

proper_divisors_sum :: Integer -> Integer
proper_divisors_sum n = sum . go 2 $ [1]
    where limit = ceiling . sqrt . (fromIntegral :: Integer -> Double) $ n
          go c r | c > limit = r
                 | m == 0 = go (c + 1) (c:d:r)
                 | otherwise = go (c + 1) r
              where (d, m) = divMod n c

amicable_number :: Integer -> Bool
amicable_number n = n' == n && n /= m
    where m = proper_divisors_sum n
          n' = proper_divisors_sum m

main :: IO()
main = print . sum . filter amicable_number $ [1 .. 10000]
