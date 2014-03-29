package eu.crydee.projecteuler.problems._1to10;

import eu.crydee.projecteuler.misc.Primes;
import eu.crydee.projecteuler.problems.Problem;
import java.math.BigInteger;
import org.apache.log4j.Logger;

public class P10 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P10.class.getCanonicalName());

    @Override
    public String getSolution() {
        return Primes.getPrimesUpTo(BigInteger.valueOf(2000000)).stream()
                .reduce(BigInteger.ZERO, (a, b) -> a.add(b), (a, b) -> a.add(b))
                .toString();
    }
    
}
