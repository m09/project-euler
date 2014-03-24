{-# OPTIONS_GHC -Wall #-}

import Data.List (sort)
import qualified Data.List.Ordered as Ord (minus, nub)

upper_bound :: Integer
upper_bound = 28124

proper_divisors_sum :: Integer -> Integer
proper_divisors_sum n = sum . go 2 $ 1
    where limit = ceiling . sqrt . (fromIntegral :: Integer -> Double) $ n
          go c r | c > limit = r
                 | m == 0 = go (c + 1) (r + c + d)
                 | otherwise = go (c + 1) r
              where (d, m) = divMod n c

is_abundant_number :: Integer -> Bool
is_abundant_number n = n < proper_divisors_sum n

abundant_numbers :: [Integer]
abundant_numbers = filter is_abundant_number $ [1 .. (upper_bound - 1)]

abundant_sums :: [Integer]
abundant_sums = [x + y | x <- abundant_numbers, y <- abundant_numbers]

main :: IO()
main = print
       . sum
       . Ord.minus [1 .. (upper_bound - 1)]
       . sort
       $ abundant_sums
