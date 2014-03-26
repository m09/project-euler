package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.misc.Divisors;
import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P21 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P21.class.getCanonicalName());

    @Override
    public String getSolution() {
        int total = 0;
        for (int i = 1; i <= 10000; i++) {
            long sumDivisors = Divisors.sumProperDivisors(i);
            if (sumDivisors > i
                    && Divisors.sumProperDivisors(sumDivisors) == i) {
                total += i;
                if (sumDivisors <= 10000) {
                    total += sumDivisors;
                }
            }
        }
        return String.valueOf(total);
    }
}
