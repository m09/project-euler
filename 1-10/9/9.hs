main = print.head $ [a * b * c | a <- [0..332],
                                 b <- [a + 1..499],
                                 let c = 1000 - a - b,
                                 a ^ 2 + b ^ 2 == c ^ 2]
