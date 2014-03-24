{-# OPTIONS_GHC -Wall #-}

main :: IO ()
main = do n <- readFile "13.data"
          let numbers = map (read :: String -> Integer) . lines $ n
          print . (read :: String -> Integer) . take 10 . show . sum $ numbers

