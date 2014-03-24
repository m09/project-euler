{-# OPTIONS_GHC -Wall #-}

import Text.ParserCombinators.Parsec
import Control.Monad
import Data.List
import Data.Either

data Value    = Two   | Three | Four | Five | Six | Seven | Eight | Nine | Ten
              | Jack  | Queen | King | Ace
              deriving (Show, Enum, Eq, Ord)

data Suit     = Clubs | Diamonds | Hearts | Spades
              deriving  (Show, Eq)

type Hand  = ((Value, Suit),
              (Value, Suit),
              (Value, Suit),
              (Value, Suit),
              (Value, Suit))

type Round = (Hand, Hand)

parseLine :: Parser Round
parseLine = do f <- parseHand
               _ <- char ' '
               s <- parseHand
               return (f, s)
            <?> "Round"

parseHand :: Parser Hand
parseHand = do c <- liftM2 (:) parseCard (count 4 (char ' ' >> parseCard))
               let [c1, c2, c3, c4, c5] = c
               return (c1, c2, c3, c4, c5)
            <?> "Hand"

parseCard :: Parser (Value, Suit)
parseCard = do v <- parseValue
               s <- parseSuit
               return (v, s)
            <?> "Card"

parseSuit :: Parser Suit
parseSuit = do s <- oneOf "SDCH"
               return $ case s
                        of 'S' -> Spades
                           'D' -> Diamonds
                           'H' -> Hearts
                           'C' -> Clubs
                           _   -> undefined
            <?> "Suit"
parseValue :: Parser Value
parseValue = do r <- oneOf "23456789TJQKA"
                return $ case r
                         of '2' -> Two
                            '3' -> Three
                            '4' -> Four
                            '5' -> Five
                            '6' -> Six
                            '7' -> Seven
                            '8' -> Eight
                            '9' -> Nine
                            'T' -> Ten
                            'J' -> Jack
                            'Q' -> Queen
                            'K' -> King
                            'A' -> Ace
                            _   -> undefined
             <?> "Value"

parsePoker :: String -> Either ParseError Round
parsePoker = parse parseLine "(unknown)"

values :: Hand -> [Value]
values ((v, _), (w, _), (x, _), (y, _), (z, _)) = [v, w, x, y, z]

suits :: Hand -> [Suit]
suits ((_, s), (_, t), (_, u), (_, v), (_, w)) = [s, t, u, v, w]

ambitus :: Int -> Hand -> Bool
ambitus n h = (fromEnum.maximum.values) h - (fromEnum.minimum.values) h == n

partValues :: Hand -> [[Int]]
partValues = sortBy comp.group.sort.map fromEnum.values
  where comp (a:as) (b:bs) | la == lb  = compare b a
                           | otherwise = compare lb la
          where la = length as
                lb = length bs
        comp _      _                  = undefined

score :: Hand -> [Int]
score = map head.partValues

nDif :: Int -> Hand -> Bool
nDif n h = (== n).length.nub.values $ h

nMostPresent :: Int -> Hand -> Bool
nMostPresent n h = (== n).length.head.partValues $ h

difAndMostPresent :: Int -> Int -> Hand -> Bool
difAndMostPresent n m = nDif n &&& nMostPresent m

isStraight :: Hand -> Bool
isStraight = nDif 5 &&& ambitus 4

isFlush :: Hand -> Bool
isFlush = (== 1).length.nub.suits

isSFlush :: Hand -> Bool
isSFlush = isStraight &&& isFlush

isRFlush :: Hand -> Bool
isRFlush = isSFlush &&& ((== Ten).minimum.values)

figure :: Hand -> (Int, [Int])
figure h | isRFlush h              = (9, s) -- royal flush
         | isSFlush h              = (8, s) -- straight flush
         | difAndMostPresent 2 4 h = (7, s) -- four of a kind
         | difAndMostPresent 2 3 h = (6, s) -- full house
         | isFlush h               = (5, (reverse.sort.map fromEnum.values) h)
         | isStraight h            = (4, s)
         | nMostPresent 3 h        = (3, s) -- three of a kind
         | difAndMostPresent 3 2 h = (2, s) -- double pair
         | nMostPresent 2 h        = (1, s) -- pair
         | otherwise               = (0, s)
  where s = score h

p1wins :: Round -> Bool
p1wins (h1, h2) | f11 == f21 = f12 > f22
                | otherwise  = f11 > f21
  where (f11, f12) = figure h1
        (f21, f22) = figure h2

(&&&) :: (a -> Bool) -> (a -> Bool) -> a -> Bool
x &&& y = \z -> x z && y z

main :: IO ()
main = do f <- readFile "54.data"
          let rounds = map parsePoker.lines $ f
              result = foldl (\x y -> if p1wins y
                                      then (x + 1 :: Integer)
                                      else x) 0 (rights rounds)
          putStr ("Player1 a gagné " ++ show result ++ " fois.\n")

