package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class P26 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P26.class.getCanonicalName());

    @Override
    public String getSolution() {
        int maxPeriod = Integer.MIN_VALUE,
                max = -1,
                period;
        for (int i = 1; i <= 1000; i++) {
            if ((period = periodLength(1, i)) > maxPeriod) {
                max = i;
                maxPeriod = period;
            }
        }
        return String.valueOf(max);
    }

    private int periodLength(long numerator, long denominator) {
        Map<Long, Integer> seen = new HashMap<>();
        Deque<Integer> digitsNumerator = Utils.getDigitsStack(numerator);
        int step = 0;
        for (; !seen.containsKey(numerator); step++) {
            seen.put(numerator, step);
            numerator = 0;
            for (int i = 0; numerator < denominator; i++) {
                numerator *= 10;
                if (i < digitsNumerator.size()) {
                    numerator += digitsNumerator.pop();
                }
            }
            logger.debug("numerator:" + numerator);
            if ((numerator %= denominator) == 0) {
                return 0;
            }
            digitsNumerator.push((int) numerator);
            logger.debug("new numerator: " + numerator);
            logger.debug("step: " + step);
            logger.debug("seen: " + seen);
            logger.debug("numerator: " + numerator);
            logger.debug("step: " + step);
            logger.debug("===");
        }
        return step - seen.get(numerator);
    }
}
