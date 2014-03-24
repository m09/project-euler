package eu.crydee.projecteuler.misc;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class Divisors {

    private static final Logger logger
            = Logger.getLogger(Divisors.class.getCanonicalName());

    static public List<Integer> getPrimeDivisors(long n) {
        List<Integer> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        result.add(1);
        long lLimit = (long) Math.ceil(Math.sqrt(n));
        if (lLimit > Integer.MAX_VALUE) {
            throw new NumberTooBigException();
        }
        int limit = (int) lLimit;
        long current = n;
        for (int prime : Primes.getPrimesUpTo(limit)) {
            if (current == 1) {
                break;
            }
            while (current % prime == 0) {
                current /= prime;
                result.add(prime);
            }
        }
        return result;
    }
}
