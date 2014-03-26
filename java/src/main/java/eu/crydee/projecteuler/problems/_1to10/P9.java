package eu.crydee.projecteuler.problems._1to10;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P9 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P9.class.getCanonicalName());

    @Override
    public String getSolution() {
        for (int i = 1; i < 333; i++) {
            for (int j = i + 1, k = 1000 - i - j; k > j; j++, k--) {
                if (k * k == i * i + j * j) {
                    return String.valueOf(i * j * k);
                }
            }
        }
        return "-1";
    }

}
