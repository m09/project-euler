{-# OPTIONS_GHC -Wall #-}

import Text.ParserCombinators.Parsec
import Control.Applicative ((<$>), liftA2)
import Data.List (transpose)

parseFile :: String -> Either ParseError [[Int]]
parseFile = parse parseLines "(unknown)"

parseLines :: Parser [[Int]]
parseLines = parseLine `endBy` newline

parseLine :: Parser [Int]
parseLine = parseNumber `sepBy` char ' '

parseNumber :: Parser Int
parseNumber = do d1 <- digit
                 d2 <- digit
                 return (read [d1, d2] :: Int)

main :: IO()
main = do f <- readFile "11.data"
          let l = parseFile f
              groups = map (<$> l) [ horizontalGroup
                                   , verticalGroup
                                   , diagonalGroup1
                                   , diagonalGroup2 ]
              total = foldl1 (liftA2 (++)) groups
          print $ maximum.map product <$> total

groupBy :: Int -> [b] -> [[b]]
groupBy n l | length l >= n = take n l
                            : groupBy n (drop 1 l)
            | otherwise     = []

groupBy4 :: [b] -> [[b]]
groupBy4 = groupBy 4

horizontalGroup :: [[a]] -> [[a]]
horizontalGroup = concatMap groupBy4

verticalGroup :: [[a]] -> [[a]]
verticalGroup = concatMap transpose . groupBy4

diagonalGroup1 :: [[a]] -> [[a]]
diagonalGroup1 = concatMap (transpose . offset1) . groupBy4

diagonalGroup2 :: [[a]] -> [[a]]
diagonalGroup2 = concatMap (transpose . offset2) . groupBy4

offset1 :: [[a]] -> [[a]]
offset1 = map (take 17) . zipWith drop [0 ..]

offset2 :: [[a]] -> [[a]]
offset2 = map (take 17) . zipWith drop [3, 2 ..]
