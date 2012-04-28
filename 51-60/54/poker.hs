import Text.ParserCombinators.Parsec
import System.IO
import Control.Monad
import Data.List
import Data.Either

data Value    = Two   | Three | Four | Five | Six | Seven | Eight | Nine | Ten
              | Jack  | Queen | King | Ace
              deriving (Show, Enum, Eq, Ord)

data Suit     = Clubs | Diamonds | Hearts | Spades
              deriving  (Show, Eq)

newtype Hand  = Hand ((Value, Suit),
                      (Value, Suit),
                      (Value, Suit),
                      (Value, Suit),
                      (Value, Suit))
              deriving (Show, Eq)

newtype Round = Round (Hand, Hand)
              deriving (Show, Eq)

parseLine :: Parser Round
parseLine = do f <- parseHand
               char ' '
               s <- parseHand
               return $ Round (f, s)
            <?> "Round"

parseHand :: Parser Hand
parseHand = do c <- liftM2 (:) parseCard (count 4 (char ' ' >> parseCard))
               let [c1, c2, c3, c4, c5] = c
               return $ Hand (c1, c2, c3, c4, c5)
            <?> "Hand"

parseCard :: Parser (Value, Suit)
parseCard = do v <- parseValue
               s <- parseSuit
               return $ (v, s)
            <?> "Card"

parseSuit :: Parser Suit
parseSuit = do s <- oneOf "SDCH"
               return $ case s
                        of 'S' -> Spades
                           'D' -> Diamonds
                           'H' -> Hearts
                           'C' -> Clubs
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
             <?> "Value"

parsePoker :: String -> Either ParseError Round
parsePoker = parse parseLine "(unknown)"

values :: Hand -> [Value]
values (Hand ((v, _), (w, _), (x, _), (y, _), (z, _))) = [v, w, x, y, z]

suits :: Hand -> [Suit]
suits (Hand ((_, s), (_, t), (_, u), (_, v), (_, w))) = [s, t, u, v, w]

ambitus :: Hand -> Int
ambitus h = (fromEnum.maximum.values) h - (fromEnum.minimum.values) h

partValues :: Hand -> [[Int]]
partValues = sortBy comp.group.sort.map fromEnum.values

score :: Hand -> [Int]
score = map head.partValues

comp :: [Int] -> [Int] -> Ordering
comp (a:as) (b:bs) | la == lb  = compare b a
                   | otherwise = compare lb la
  where la = length as
        lb = length bs

nDif :: Hand -> Int
nDif = length.nub.values

nMostPresent :: Hand -> Int
nMostPresent = length.head.partValues

difAndMostPresent :: Int -> Int -> (Hand -> Bool)
difAndMostPresent n m = ((== n).nDif) &&& ((== m).nMostPresent)

isOnePair :: Hand -> Bool
isOnePair = difAndMostPresent 4 2

isTwoPairs :: Hand -> Bool
isTwoPairs = difAndMostPresent 3 2

isThreeOfAKind :: Hand -> Bool
isThreeOfAKind = difAndMostPresent 3 3

isStraight :: Hand -> Bool
isStraight h | nDif h == 5 && ambitus h == 4  = True
             | otherwise                      = False

isFlush :: Hand -> Bool
isFlush = (== 1).length.nub.suits

isFullHouse :: Hand -> Bool
isFullHouse = difAndMostPresent 2 3

isFourOfAKind :: Hand -> Bool
isFourOfAKind = difAndMostPresent 2 4

isStraightFlush :: Hand -> Bool
isStraightFlush = isStraight &&& isFlush

isRoyalFlush :: Hand -> Bool
isRoyalFlush = isStraightFlush &&& ((== Ten).minimum.values)

figure :: Hand -> (Int, [Int])
figure h | isRoyalFlush h    = (9, s)
         | isStraightFlush h = (8, s)
         | isFourOfAKind h   = (7, s)
         | isFullHouse h     = (6, s)
         | isFlush h         = (5, (reverse.sort.map fromEnum.values) h)
         | isStraight h      = (4, s)
         | isThreeOfAKind h  = (3, s)
         | isTwoPairs h      = (2, s)
         | isOnePair h       = (1, s)
         | otherwise         = (0, s)
  where s = score h

p1wins :: Round -> Bool
p1wins (Round (h1, h2)) | f11 == f21 = f12 > f22
                        | otherwise  = f11 > f21
  where (f11, f12) = figure h1
        (f21, f22) = figure h2

(&&&) :: (a -> Bool) -> (a -> Bool) -> a -> Bool
x &&& y = \z -> x z && y z

main = do handle <- openFile "poker.txt" ReadMode
          rawData <- hGetContents handle
          let rawRounds = lines rawData
              rounds = map parsePoker rawRounds
              result = foldl (\x y -> if p1wins y
                                      then x + 1
                                      else x) 0 (rights rounds)
          putStr ("Player1 a gagné " ++ show result ++ " fois.\n")
          hClose handle
