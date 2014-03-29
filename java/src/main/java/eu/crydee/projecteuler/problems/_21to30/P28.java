package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P28 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P28.class.getCanonicalName());

    @Override
    public String getSolution() {
        long r = 1;
        for (int i = 3; i < 1002; i += 2) {
            r += i * i * 4 - (i - 1) * 6;
        }
        return String.valueOf(r);
    }
}
