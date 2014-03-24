{-# OPTIONS_GHC -Wall #-}

import Data.Char

main :: IO()
main = print . sum . map digitToInt . show . product $ [(1 :: Integer) .. 100]
