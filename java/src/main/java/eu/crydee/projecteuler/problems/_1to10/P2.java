package eu.crydee.projecteuler.problems._1to10;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P2 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P2.class.getCanonicalName());

    @Override
    public String getSolution() {
        int previous = 0,
                current = 1,
                total = 0;

        do {
            if (current % 2 == 0) {
                total += current;
            }
            int temp = previous;
            previous = current;
            current += temp;
        } while (current < 4000000);

        return String.valueOf(total);
    }

}
