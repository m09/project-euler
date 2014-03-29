package eu.crydee.projecteuler.problems._1to10;

import com.google.common.collect.Ordering;
import eu.crydee.projecteuler.misc.Divisors;
import eu.crydee.projecteuler.problems.Problem;
import java.math.BigInteger;
import org.apache.log4j.Logger;

public class P3 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P3.class.getCanonicalName());

    @Override
    public String getSolution() {
        return Divisors.getPrimeDivisors(BigInteger.valueOf(600851475143l))
                .stream()
                .max(Ordering.natural())
                .get()
                .toString();
    }

}
