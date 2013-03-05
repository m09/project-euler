As usual, we want the compiler to help us

> {-# OPTIONS_GHC -Wall #-}

We'll do some digits calculations

> import Data.Char (digitToInt)

And we'll need to memoize values

> import qualified Data.MemoCombinators as Memo

First, we need to say how to go from one value to the next one.

> nextStep :: Int -> Int
> nextStep = sum . map ((^ (2 :: Int)) . digitToInt) . show

Then, we use memoization to compute the end of the chain quickly.

We limit it to 567 because higher values will only be seen once.

Reason is that 9999999 has a squared digits sum value of 567.

> chainEndsWith89 :: Int -> Bool
> chainEndsWith89 = Memo.arrayRange (1, 567) worker
>   where
>     worker 89    = True
>     worker 1     = False
>     worker x     = chainEndsWith89 $ nextStep x

Main program is straight-forward once those functions are defined.

> main :: IO()
> main = print . length . filter chainEndsWith89 $ [1 .. 10000000]
