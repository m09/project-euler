{-# OPTIONS_GHC -Wall #-}

-- Must compile with -O2 before use. Interpreted mode won't cut it.

import Data.List (maximumBy)
import Data.Word (Word64)
import Data.Function (on)

main :: IO ()
main = print
     . fst
     . maximumBy (compare `on` snd)
     . map syr
     $ ([500001..1000000] :: [Word64])

syr :: Word64 -> (Word64, Word64)
syr n = (n, go n)
  where go 1             = 0
        go m | even m    = 1 + go (m `div` 2)
             | otherwise = 1 + go (3 * m + 1)

