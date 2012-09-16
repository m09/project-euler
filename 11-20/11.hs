import Text.ParserCombinators.Parsec
import System.IO
import Control.Applicative ((<$>), (<*>), pure, liftA2)
import Data.List (genericLength, genericTake, genericDrop, transpose)

parseFile :: String -> Either ParseError [[Int]]
parseFile = parse parseLines "(unknown)"

parseLines :: Parser [[Int]]
parseLines = parseLine `endBy` newline

parseLine :: Parser [Int]
parseLine = parseNumber `sepBy` (char ' ')

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

groupBy :: Integral a => a -> [b] -> [[b]]
groupBy n l | genericLength l >= n = genericTake n l
                                     : groupBy n (genericDrop 1 l)
            | otherwise            = []

groupBy4 :: [b] -> [[b]]
groupBy4 = groupBy 4

horizontalGroup = concat.map groupBy4
verticalGroup = concat.map transpose.groupBy4
diagonalGroup1 = concat.map transpose.map offset1.groupBy4
diagonalGroup2 = concat.map transpose.map offset2.groupBy4

offset1 = map (take 17).zipWith drop [0 ..]
offset2 = map (take 17).zipWith drop [3, 2 ..]
