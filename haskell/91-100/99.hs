{-# OPTIONS_GHC -Wall #-}

import Data.List  ( maximumBy
                  , findIndex )
import Data.Maybe ( fromJust  )
import Data.Ord   ( comparing )

split :: String -> (Integer, Integer)
split s = let l = fromJust . findIndex (== ',') $ s
          in (read (take l s), read (drop (l + 1) s))

main :: IO ()
main = do f <- readFile "99.data"
          let t = zip ([1 .. ] :: [Integer])
                  . map (uncurry (^) . split) . lines $ f
          print . fst . maximumBy (comparing snd) $ t
