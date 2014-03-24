{-# OPTIONS_GHC -Wall #-}

import Data.Char

main :: IO ()
main = do n <- readFile "8.data"
          let number = map digitToInt
                     . concat
                     . lines
                     $ n
          print . snd $ foldl (\(l, s) c -> let l' = tail l ++ [c]
                                            in  (l', max (product l') s))
                              (0 : take 4 number, 0)
                              (drop 4 number)

