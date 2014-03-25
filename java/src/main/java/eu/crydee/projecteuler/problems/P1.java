package eu.crydee.projecteuler.problems;

import org.apache.log4j.Logger;

public class P1 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P1.class.getCanonicalName());

    @Override
    public String getSolution() {
        int total = 0;
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0
                    || i % 5 == 0) {
                total += i;
            }
        }
        return String.valueOf(total);
    }
    
}
