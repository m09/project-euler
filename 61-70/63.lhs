We need help from the compiler:

> {-# OPTIONS_GHC -Wall #-}

And we'll need some nice functions down the road:

> import Data.List ( genericLength
>                  , nub )

First, we need to define a function to get the number of digits of a
positive integer. This is done nicely with genericLength instead of
length so that Integers are handled easily (and not only Ints):

> integerLength :: Integer -> Integer
> integerLength n = genericLength $ show n

Now we need to think a bit about our problem. First we can note that
10 ^ e where e is a positive integer always has e + 1 digits. So we
can safely say that if 9 ^ e has less than e digits, e is an upper
born of the exponents that could work to generate our list. If we
compute our list from exponents starting from 1 up to infinity, we can
stop at the first exponent that is an upper born.

Let's now define a function that retrieves the list of numbers that
interest us for a given exponent and that also returns a boolean that
indicates if we have to go on or not (True means that we still did not
hit an upper born), we can do that efficiently since we don't need to
test more than 9 powers per exponent and since we have properly
defined our upper born:

> stepN :: Integer -> ([Integer], Bool)
> stepN e = go 1 []
>     where
>       go n r = let p = n ^ e
>                    l = compare (integerLength p) e
>                in case l of
>                     LT -> if n == 9 then (r, False) else go (n + 1) r
>                     EQ -> go (n + 1) (p:r)
>                     GT -> (r, True)

Read from the bottom to follow function composition:

> main :: IO ()
> main = print
>        . length

Now the solution is just the length of the list.

>        . nub

We don't want duplicates.

>        . concat

We have a list of lists of numbers that interest us. Let's flatten it.

>        . map fst

Let's remove the flags, we don't need them anymore.

>        . takeWhile (\x -> snd x)

If we hit an upper born, we'll stop.

>        . map stepN

And we'll apply the function we just defined.

>        $ [1, 2 .. ]

We'll iterate from 1 up to infinity.
