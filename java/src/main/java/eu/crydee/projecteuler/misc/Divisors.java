package eu.crydee.projecteuler.misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    static public Integer numberOfProperDivisors(long n) {
        long limit = Math.round(Math.sqrt(n));
        int total = 0;
        for (long i = 1; i < limit; i++) {
            if (n % i == 0) {
                total += 2;
            }
        }
        if (limit * limit == n) {
            total += 1;
        }
        return total;
    }

    static public Set<Long> properDivisors(long n) {
        long limit = Math.round(Math.sqrt(n));
        Set<Long> result = new HashSet<>();
        for (long i = 1; i <= limit; i++) {
            if (n % i == 0) {
                result.add(i);
                result.add(n / i);
            }
        }
        return result;
    }

    static public long sumProperDivisors(long n) {
        if (n == 0) {
            return Long.MAX_VALUE;
        }
        if (n == 1) {
            return 0;
        }
        if (n < 0) {
            n = -n;
        }
        long limit = Math.round(Math.sqrt(n)),
                total = 1;
        for (long i = 2; i < limit; i++) {
            if (n % i == 0) {
                total += i;
                total += n / i;
            }
        }
        if (limit * limit == n) {
            total += limit;
        }
        return total;
    }
}
