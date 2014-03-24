{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = print $ (28433 * 2 ^ (7830457 :: Integer) + 1) `mod` 10 ^ (10 :: Integer)
