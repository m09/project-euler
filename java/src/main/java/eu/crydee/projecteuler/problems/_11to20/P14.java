package eu.crydee.projecteuler.problems._11to20;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P14 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P14.class.getCanonicalName());

    @Override
    public String getSolution() {
        int maxSteps = Integer.MIN_VALUE, max = -1, steps;
        for (int i = 1; i < 1000000; i ++) {
            if ((steps = nStepsCollatz(i)) > maxSteps) {
                maxSteps = steps;
                max = i;
            }
        }
        return String.valueOf(max);
    }
    
    private int nStepsCollatz(long l) {
        int operations = 0;
        while (l != 1) {
            if (l % 2 == 0) {
                l /= 2;
            } else {
                l = l * 3 + 1;
            }
            operations++;
        }
        return operations;
    }
}
