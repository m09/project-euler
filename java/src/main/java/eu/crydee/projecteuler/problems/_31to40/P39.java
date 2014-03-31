package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P39 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P39.class.getCanonicalName());

    @Override
    public String getSolution() {
        int maxSolutions = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int p = 12; p <= 1000; p++) {
            int solutions = 0;
            int iLimit = p;
            for (int i = 1; i < iLimit; i++) {
                for (int j = i; j < p; j++) {
                    int k = p - i - j;
                    if (k * k == i * i + j * j) {
                        logger.debug("p/i/j/k"
                                + p
                                + "/"
                                + i
                                + "/"
                                + j
                                + "/"
                                + k);
                        solutions++;
                    }
                }
            }
            if (solutions > maxSolutions) {
                maxSolutions = solutions;
                max = p;
            }
        }
        return String.valueOf(max);
    }
}
