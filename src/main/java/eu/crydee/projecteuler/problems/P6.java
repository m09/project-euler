package eu.crydee.projecteuler.problems;

import org.apache.log4j.Logger;

public class P6 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P6.class.getCanonicalName());

    @Override
    public String getSolution() {
        int sumOfSquares = 0,
                squareOfSum = 0;
        for (int i = 1; i < 101; i++) {
            sumOfSquares += i * i;
            squareOfSum += i;
        }
        return String.valueOf(squareOfSum * squareOfSum - sumOfSquares);
    }

}
