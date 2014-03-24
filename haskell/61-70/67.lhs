WARNING: strictness analysis is required for this program to behave
correctly. Please use ghc --make 67.lhs -O or equivalent to produce an
executable.

As usual, we want some help from the compiler and we need
some libraries.

> {-# OPTIONS_GHC -Wall #-}
>
> import Data.Either
> import Data.StableMemo
> import Text.ParserCombinators.Parsec

We'll now need a tree data structure to represent correctly our data:

> data Tree a = Leaf a | Node a (Tree a) (Tree a)

And the tools to parse the "triangle" of numbers into this tree.
First we have to get the data into lists from the file. This is done
with Parsec:

> parser :: String -> Either ParseError [Int]
> parser = parse parserLevel "unknown"
> 
> parserLevel :: Parser [Int]
> parserLevel = sepBy1 parserValue (char ' ')
> 
> parserValue :: Parser Int
> parserValue = many1 digit >>= (return . (read :: String -> Int))

Then we need to transform the lists into a tree. The basic algorithm
to do so will be to go through the tree two rows at a time, and use
the bottom row to fill the top row (l2t). Then, for each pair of rows,
we'll go through row at the same time to contruct the Nodes of the top
row (l2n). We'll also directly construc a list of leaves to initialize
the process when we encounter the last row (l2l).

> lists2tree :: [[a]] -> Tree a
> lists2tree = head . l2t
> 
> l2t :: [[a]] -> [Tree a]
> l2t (xs:ys:zss) = l2n xs ts t
>     where (t:ts) = l2t (ys:zss)
> l2t (x:[])      = l2l x
> l2t []          = undefined
> 
> l2n :: [a] -> [Tree a] -> Tree a -> [Tree a]
> l2n (x:xs) (y:ys) p = Node x p y:l2n xs ys y
> l2n []     []     _ = []
> l2n _      _      _ = undefined
> 
> l2l :: [a] -> [Tree a]
> l2l = map (\l -> Leaf l)

Once we have a tree, calculating the maximal sum is trivial. But it's
exponential in its naive form, so we need memoization:
 
> calculate :: (Ord a, Num a) => Tree a => a
> calculate = memo go
>     where go (Node v l r) = v + (max (calculate l) (calculate r))
>           go (Leaf v) = v

The current main program to use those functions together has a line
added to check the equality of two Nodes that should be equal. It
should print True but prints False. Hence the memoization can't occur
and the program is exponential.

> main :: IO()
> main = do f <- readFile "67.data"
>           let levels = rights . map parser . lines $ f
>               tree = lists2tree levels
>           print $ calculate tree
