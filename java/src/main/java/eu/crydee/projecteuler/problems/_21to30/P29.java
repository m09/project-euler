package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.problems.Problem;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

public class P29 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P29.class.getCanonicalName());

    @Override
    public String getSolution() {
        final Set<BigInteger> results = new HashSet<>();
        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                results.add(BigInteger.valueOf(a).pow(b));
            }
        }
        return String.valueOf(results.size());
    }
}
