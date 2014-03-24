package eu.crydee.projecteuler.problems;

import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 * Pen & pencil solution.
 *
 * To handle this one we first consider all the primes: they must be factors in
 * the solution. Then we find the minimal set of prime factors to handle the
 * rest. Starting by 16 and following up by 9 satisfies all the others.
 */
public class P5 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P5.class.getCanonicalName());

    @Override
    public String getSolution() {
        return Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 2, 2, 2, 3).stream()
                .reduce(1, (a, b) -> a * b, (a, b) -> a * b)
                .toString();
    }

}
