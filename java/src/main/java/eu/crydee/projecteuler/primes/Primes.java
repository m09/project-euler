package eu.crydee.projecteuler.primes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

public class Primes {

    private static final Logger logger
            = Logger.getLogger(Primes.class.getCanonicalName());

    public static Stream<BigInteger> stream() {
        return Stream.generate(new PrimesSupplier());
    }

    public static List<BigInteger> getPrimesUpTo(BigInteger to) {
        ArrayList<BigInteger> result = new ArrayList<>();
        Iterator<BigInteger> it = stream().iterator();
        for (BigInteger prime = it.next();; prime = it.next()) {
            if (prime.compareTo(to) > 0) {
                return result;
            }
            result.add(prime);
        }
    }

    public static boolean isPrime(long n) {
        if (n < 0) {
            n = -n;
        }
        if (n == 0 || n == 1 || n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        long limit = (long) Math.ceil(Math.sqrt(n));
        for (long i = 5; i <= limit; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
