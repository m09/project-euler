package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.primes.Primes;
import eu.crydee.projecteuler.problems.Problem;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;

public class P37 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P37.class.getCanonicalName());

    @Override
    public String getSolution() {
        Iterator<BigInteger> it = Primes.stream().iterator();
        BigInteger result = BigInteger.ZERO;
        Set<BigInteger> seen = new HashSet<>();
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            seen.add(it.next());
        }
        main:
        while (counter != 11) {
            BigInteger current = it.next();
            seen.add(current);
            BigInteger c = BigInteger.ONE;
            while ((c = c.multiply(BigInteger.TEN)).compareTo(current) < 0) {
                BigInteger[] divMod = current.divideAndRemainder(c);
                if (!seen.contains(divMod[0]) || !seen.contains(divMod[1])) {
                    continue main;
                }
            }
            counter++;
            result = result.add(current);
        }
        return result.toString();
    }
}
