{-# OPTIONS_GHC -Wall #-}

main :: IO()
main = print "lol"

try :: [Int] -> [Int]
try [a, b, c, d, e, f, g, h, i, j] | 
    let t = a + b + c
        u = d + c + e
        v = f + e + g
        w = h + g + i
        x = j + i + b
