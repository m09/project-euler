package eu.crydee.projecteuler.misc;

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
}
