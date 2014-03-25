package eu.crydee.projecteuler.problems;

import com.google.common.math.BigIntegerMath;
import org.apache.log4j.Logger;

/**
 * Pen & paper solution.
 *
 * This one is 40 choose 20.
 */
public class P15 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P15.class.getCanonicalName());

    @Override
    public String getSolution() {
        return BigIntegerMath.factorial(40)
                .divide(BigIntegerMath.factorial(20).pow(2))
                .toString();
    }
}
