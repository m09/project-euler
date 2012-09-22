isPalindrome :: (Integral a, Show a) => a -> Bool
isPalindrome n = let ns = show n in ns == reverse ns

stepper :: (Integral a, Show a, Read a) => a -> a
stepper n = n + (read . reverse . show $ n)

isLychrel :: (Integral a, Show a, Read a) => a -> Bool
isLychrel = not
          . any isPalindrome
          . take 50
          . tail
          . iterate stepper

main = print . length . filter id . map isLychrel $ [1..9999]
