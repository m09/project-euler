package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.primes.Primes;
import eu.crydee.projecteuler.problems.Problem;
import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

public class P35 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P35.class.getCanonicalName());

    @Override
    public String getSolution() {
        Set<Long> primes = Primes.getPrimesUpTo(BigInteger.valueOf(1000000))
                .stream()
                .map(n -> n.longValue())
                .collect(Collectors.toSet());
        int result = 0;
        main:
        for (Long prime : primes) {
            long rotation = prime;
            while ((rotation = rotate(rotation)) != prime) {
                if (!primes.contains(rotation)) {
                    continue main;
                }
            }
            result++;
        }
        return String.valueOf(result);
    }

    private long rotate(long n) {
        long lastDigit = n % 10,
                allButFirst = n / 10;
        if (allButFirst == 0) {
            return n;
        }
        return Long.parseLong(String.valueOf(lastDigit) + allButFirst);
    }
}
