{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = print
     . sum
     . filter (\x -> x `mod` 3 == 0 || x `mod` 5 == 0)
     $ ([1..999] :: [Int])
