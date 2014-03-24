{-# OPTIONS_GHC -Wall #-}

import Data.Char
import Data.List (sort)
import Text.ParserCombinators.Parsec

parser :: String -> Either ParseError [String]
parser = parse parserLine "unknown"

parserLine :: Parser [String]
parserLine = sepBy1 parserWord (char ',')

parserWord :: Parser String
parserWord = do _ <- char '"'
                w <- many1 upper
                _ <- char '"'
                return w

scoreChar :: Char -> Integer
scoreChar = (subtract 64) . fromIntegral . ord

scoreWord :: String -> Integer
scoreWord = sum . map scoreChar

scoreList :: [String] -> Integer
scoreList = sum . zipWith (*) [1 .. ] . map scoreWord . sort

main :: IO()
main = do f <- readFile "22.data"
          let parsed = parser . head . lines $ f
          case parsed of
            Left e -> print e
            Right w -> print . scoreList $ w
