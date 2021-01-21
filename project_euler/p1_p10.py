from collections import Counter
from functools import reduce
from math import sqrt
from operator import mul, or_
from typing import Counter as CounterType, List

from project_euler import app


@app.command()
def p1() -> None:
    print(sum(s for s in range(1000) if s % 3 == 0 or s % 5 == 0))


@app.command()
def p2() -> None:
    a, b = 0, 1
    total = 0
    while b <= 4_000_000:
        a, b = b, a + b
        if b % 2 == 0:
            total += b
    print(total)


@app.command()
def p3() -> None:
    n = 600_851_475_143
    current = n
    primes = [2]
    i = 1
    while current != 1:
        i += 2
        for prime in primes:
            if i % prime == 0:
                break
        else:
            primes.append(i)
            while current % i == 0:
                current //= i
    print(i)


@app.command()
def p4() -> None:
    result = None
    for i in range(100, 1000):
        for j in range(100, 1000):
            product = i * j
            product_string = str(product)
            if product_string == product_string[::-1]:
                if result is None or product > result:
                    result = product
    print(result)


@app.command()
def p5() -> None:
    factors: List[Counter] = []
    dividers: List[int] = []
    for i in range(2, 20):
        counter: CounterType[int] = Counter()
        for divider in dividers:
            while i % divider == 0:
                counter[divider] += 1
                i //= divider
        if i != 1:
            counter[i] += 1
            dividers.append(i)
        factors.append(counter)
    all_factors: CounterType[int] = reduce(or_, factors, Counter())
    print(reduce(mul, (k ** v for k, v in all_factors.items()), 1))


@app.command()
def p6() -> None:
    sum_of_squares = sum(n ** 2 for n in range(101))
    square_of_sum = sum(range(101)) ** 2
    print(square_of_sum - sum_of_squares)


@app.command()
def p7() -> None:
    primes = [2]
    n = 3
    while len(primes) < 10_001:
        for prime in primes:
            if n % prime == 0:
                break
        else:
            primes.append(n)
        n += 2
    print(primes[-1])


@app.command()
def p8() -> None:
    number = (
        "73167176531330624919225119674426574742355349194934"
        "96983520312774506326239578318016984801869478851843"
        "85861560789112949495459501737958331952853208805511"
        "12540698747158523863050715693290963295227443043557"
        "66896648950445244523161731856403098711121722383113"
        "62229893423380308135336276614282806444486645238749"
        "30358907296290491560440772390713810515859307960866"
        "70172427121883998797908792274921901699720888093776"
        "65727333001053367881220235421809751254540594752243"
        "52584907711670556013604839586446706324415722155397"
        "53697817977846174064955149290862569321978468622482"
        "83972241375657056057490261407972968652414535100474"
        "82166370484403199890008895243450658541227588666881"
        "16427171479924442928230863465674813919123162824586"
        "17866458359124566529476545682848912883142607690042"
        "24219022671055626321111109370544217506941658960408"
        "07198403850962455444362981230987879927244284909188"
        "84580156166097919133875499200524063689912560717606"
        "05886116467109405077541002256983155200055935729725"
        "71636269561882670428252483600823257530420752963450"
    )
    max_product = 0
    for span in (slice(start, start + 13) for start in range(len(number) - 13)):
        product = reduce(mul, map(int, number[span]), 1)
        if product > max_product:
            max_product = product
    print(max_product)


@app.command()
def p9() -> None:
    for a in range(1, 333):
        for b in range(a + 1, 500):
            c = 1000 - a - b
            if a ** 2 + b ** 2 == c ** 2:
                print(reduce(mul, [a, b, c], 1))
                return


@app.command()
def p10() -> None:
    primes = [2]
    n = 3
    while n < 2_000_000:
        limit = int(round(sqrt(n)))
        is_prime = True
        for prime in primes:
            if prime > limit:
                break
            elif n % prime == 0:
                is_prime = False
                break
        if is_prime:
            primes.append(n)
        n += 2
    print(sum(primes))
