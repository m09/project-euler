{-# OPTIONS_GHC -Wall #-}

import Data.List
import Data.Function

type Coords = (Int, Int)
type Paths = Integer

nextStep :: Int -> (Coords, Paths) -> [(Coords, Paths)]
nextStep l ((x, y), n) | x == l    = [((l, y + 1), n)]
                       | y == l    = [((x + 1, l), n)]
                       | otherwise = [((x + 1, y), n), ((x, y + 1), n)]

main :: IO()
main = print . worker $ [((0, 0), 1)]
  where worker [((20, 20), n)] = n
        worker l = worker (regroup (l >>= nextStep 20))

regroup :: [(Coords, Paths)] -> [(Coords, Paths)]
regroup = map (foldl1 (\(c, n) (_, m) -> (c, n + m)))
        . groupBy ((==) `on` fst)
        . sort
