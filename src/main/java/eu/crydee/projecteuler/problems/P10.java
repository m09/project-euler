package eu.crydee.projecteuler.problems;

import eu.crydee.projecteuler.misc.Primes;
import org.apache.log4j.Logger;

public class P10 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P10.class.getCanonicalName());

    @Override
    public String getSolution() {
        return Primes.getPrimesUpTo(2000000).stream()
                .reduce(0l, (r, i) -> r + i, (a, b) -> a + b)
                .toString();
    }
    
}
