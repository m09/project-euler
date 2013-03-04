As usual, we want the compiler to help us

> {-# OPTIONS_GHC -Wall #-}

Then, we need Parsec to parse the data file.

> import Text.Parsec
> import Text.Parsec.String

Later on we'll make use of ordered streams and Data.List.Ordered is the perfect fit to deal with them.

To install Data.List.Ordered, you can use `cabal install data-ordlist`.

See [the documentation](http://hackage.haskell.org/package/data-ordlist) for more details.

> import Data.List.Ordered (has)

First, we'll define two parse functions.

The first one is designed to take the input line and split it on commas

> parseLine :: Parser [String]
> parseLine = parseWord `sepBy` char ','

The second one is designed to take a token and return it without its surrounding double quotes.

> parseWord :: Parser String
> parseWord = do
>   _ <- char '"'
>   word <- many letter
>   _ <- char '"'
>   return word

Now, we define a function that returns the score of the argument word.

The score is defined as the sum of the letters scores where the letter score is [1-26] for [A-Z].

To do that, we exploit the fact that fromEnum of [A-Z] is [65-90] and that [65-90] - 64 = [1-26].

> wordScore :: String -> Int
> wordScore = sum . map (subtract 64 . fromEnum)

Then we create the list of triangle numbers.
To do that we note that triangle numbers are numbers that are separated by a larger and larger increment: from 2 - 1 = 1, 4 - 2 = 2, 7 - 4 = 3, 11 - 7 = 4, etc. The differences of consecutive triangle numbers are indeed [1..].

A convenient function exists to model this behaviour, it's `scanl`. It's part of the Data.List package.

> triangleNumbers :: [Int]
> triangleNumbers = scanl1 (+) [1..]

Now we have all the tools needed to answer the problem.

The main program is just the straight-forward application of the above functions.

> main :: IO()
> main = do
>   f <- readFile "42.data"
>   case parse parseLine "42.data" f of
>     Left e -> print e
>     Right wordsList -> do
>       let wordScores    = map    wordScore             wordsList
>           triangleWords = filter (has triangleNumbers) wordScores
>       print $ length triangleWords

