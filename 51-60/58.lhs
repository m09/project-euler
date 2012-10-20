We don't handle the primes ourselves and we want help from the
compiler.

> {-# OPTIONS_GHC -Wall #-}
> 
> import Math.NumberTheory.Primes

First, we construct in diags the stream of the numbers that are on the
diagonals (minus the SE diagonal). To do that, we notice that those
numbers get incremented twice in a row by a certain number, and then
again with this number * 2 + 2 (remember, we skip the SE diag),
etc. concatMap applied on the correct arguments will easily traduce
this behaviour to produce a list of increments.

Then we just need to aggregate all that with a scanl (+) and we have
our list.

> primesDiags :: [Int]
> primesDiags = go diags (map fromInteger primes)
>   where
>     diags :: [Integer]
>     diags = scanl (+) 3
>             . concatMap (\n -> [n, n, n * 2 + 2])
>             $ [2, 4..] :: [Integer]

Once we have this list, we map all the numbers to 0 if the number is
composite and 1 if the number is prime. To do that efficiently, we use
a library to provide the stream of primes and map the two lists by
running through them once only. It's to be noted that the performance
of the program is decided here : we could instead use a prime test on
each number, or use another library to generate primes, netting
potentially better perfs.

>     go dss@(d:ds) pss@(p:ps) | d < p = 0:go ds pss
>                              | d > p = go dss ps
>                              | otherwise = 1:go ds ps

Then we tell ghc we know why our pattern matching is incomplete

>     go _ _ = undefined -- we operate on streams

Now we have everything we need to answer the problem. Next step is to
find at which square we cross the specific limit we seek to spot. To
do that we simply update an accumulator to represent the number of
primes we met up until this point and we keep track of the index of
the square we're at too.

We start the recursion at 2 to just keep track of factorized
behaviour.  That's why we skipped the one during the generation of
primesDiags and since this item is 1 we set our acc to 0 (1 isn't
prime).

> nthSquare :: Int
> nthSquare = go 2 primesDiags 0
>   where
>     go n (w:x:y:ds) primeN | 9 * primeN' < compositeN' = n
>                            | otherwise = go (n + 1) ds primeN'
>       where
>         total = 4 * n - 3
>         delta = sum [w, x, y]
>         primeN' = primeN + delta
>         compositeN' = total - primeN'
>     go _ _ _ = undefined -- we operate on streams

Then, once we've spot the correct square, its side length is obtained
by doubling its index and substracting one.

> main :: IO ()
> main = print $ nthSquare * 2 - 1
