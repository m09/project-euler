import System.IO
import Data.Char

main = do n <- readFile "number"
          let number = map digitToInt.concat.lines $ n
          print.snd $ foldl (\(l, s) c -> let l' = tail l ++ [c]
                                          in  (l', max (product l') s))
                            (0:(take 4 number), 0)
                            number

