{-# OPTIONS_GHC -Wall #-}

import Data.Char

main :: IO ()
main = print
     . sum
     . map digitToInt
     . show
     . ((2 ^) :: Integer -> Integer)
     $ 1000
