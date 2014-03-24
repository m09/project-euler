package eu.crydee.projecteuler.problems;

import eu.crydee.projecteuler.misc.Divisors;
import org.apache.log4j.Logger;

public class P12 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P12.class.getCanonicalName());

    @Override
    @SuppressWarnings("empty-statement")
    public String getSolution() {
        long t = 1l;
        for (long i = 2l; Divisors.numberOfDivisors(t) <= 500; t += i, i++);
        return String.valueOf(t);
    }
}
