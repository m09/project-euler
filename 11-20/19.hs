{-# OPTIONS_GHC -Wall #-}

type Year      = Int
type Month     = Int
newtype QMonth = QMonth (Year, Month) deriving (Show, Eq, Ord)

isLeapYear :: Year -> Bool
isLeapYear y = divises 4 y && (not . divises 100) y || divises 400 y 

divises :: Integral a => a -> a -> Bool
divises x y = y `mod` x == 0

nDays :: QMonth -> Int
nDays (QMonth (y, 2)) | isLeapYear y = 29
                      | otherwise    = 28
nDays (QMonth (_, m))                = 31 - ((m - 1) `mod` 7) `mod` 2

nextMonth :: QMonth -> QMonth
nextMonth (QMonth (y, 12)) = QMonth (y + 1  , 1    )
nextMonth (QMonth (y, m )) = QMonth (y      , m + 1)

main :: IO ()
main = print
     . length
     . filter (== 6)
     . drop 12
     . scanl (\o m -> (o + nDays m) `mod` 7) 0
     . takeWhile (< QMonth (2001, 1))
     . iterate nextMonth
     $ QMonth (1900, 1)
