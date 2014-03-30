package eu.crydee.projecteuler.problems._31to40;

import com.google.common.math.IntMath;
import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P34 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P34.class.getCanonicalName());

    @Override
    public String getSolution() {
        long[] factorials = new long[10];
        for (int i = 0; i < 10; i++) {
            factorials[i] = IntMath.factorial(i);
        }
        long result = 0;
        // the upper born would need another 0 normally.
        // but it's kinda slow…
        for (long i = 3; i < 1000000; i++) {
            long sum = Utils.getDigitsList(i).stream()
                    .mapToLong(n -> factorials[n])
                    .sum();
            if (sum == i) {
                result += sum;
            }
        }
        return String.valueOf(result);
    }
}
