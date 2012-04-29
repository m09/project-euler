main = print.(\x -> sum [1..x] ^ 2 - sum [y ^ 2 | y <- [1..x]]) $ 100
