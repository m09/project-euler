package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P36 implements Problem {
    
    private static final Logger logger
            = Logger.getLogger(P36.class.getCanonicalName());
    
    @Override
    public String getSolution() {
        long result = 0;
        for (int i = 0; i < 1000000; i++) {
            String decimal = Integer.toString(i),
                    binary = Integer.toString(i, 2);
            if (decimal.equals(reverse(decimal))
                    && binary.equals(reverse(binary))) {
                result += i;
            }
        }
        return String.valueOf(result);
    }
    
    private String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }
}
