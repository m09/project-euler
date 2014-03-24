package eu.crydee.projecteuler.problems;

import com.google.common.collect.Ordering;
import eu.crydee.projecteuler.misc.Divisors;
import org.apache.log4j.Logger;

public class P3 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P3.class.getCanonicalName());

    @Override
    public String getSolution() {
        return Divisors.getPrimeDivisors(600851475143l).stream()
                .max(Ordering.natural())
                .orElse(-1)
                .toString();
    }

}
