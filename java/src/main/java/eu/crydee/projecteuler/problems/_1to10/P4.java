package eu.crydee.projecteuler.problems._1to10;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class P4 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P4.class.getCanonicalName());

    @Override
    public String getSolution() {
        int max = Integer.MIN_VALUE;
        for (int i = 100; i < 1000; i++) {
            for (int j = i; j < 1000; j++) {
                int mul = i * j;
                char[] normal = Utils.splitNumberIntoDigits(mul),
                        reversed = new char[normal.length];
                System.arraycopy(normal, 0, reversed, 0, normal.length);
                ArrayUtils.reverse(reversed);
                if (Arrays.equals(normal, reversed)) {
                    if (mul > max) {
                        max = mul;
                    }
                }
            }
        }
        return String.valueOf(max);
    }

}
