package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P30 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P30.class.getCanonicalName());

    @Override
    public String getSolution() {
        long result = 0;
        // upper born is 9 ^ 5 * 6 in our case, 9 ^ 5 * 7
        // only has 6 digits.
        for (long i = 2; i < 354295; i++) {
            long sum = Utils.getDigitsList(i).stream()
                    .mapToLong(d -> d * d * d * d * d)
                    .sum();
            if (sum == i) {
                result += i;
            }
        }
        return String.valueOf(result);
    }
}
