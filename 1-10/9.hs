{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = print
     . head
     $ [ a * b * c | a <- ([0..332] :: [Int])
                   , b <- [a + 1..499]
                   , let c = 1000 - a - b
                   , a * a + b * b == c * c ]
