main = print.maximum $ [p | a <- [100..999], b <- [a..999], let p = a * b,
                            (reverse.show) p == show p]

