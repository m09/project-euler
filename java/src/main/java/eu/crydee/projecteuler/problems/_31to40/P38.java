package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.log4j.Logger;

public class P38 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P38.class.getCanonicalName());

    @Override
    public String getSolution() {
        final Set<Integer> digits = IntStream.rangeClosed(1, 9).boxed()
                .collect(Collectors.toSet());
        long maxProduct = Integer.MIN_VALUE;
        String result = "nothing";
        for (int n = 2; n <= 5; n++) {
            int lower = (int) Math.round(Math.pow(10, 9 / n - 1)),
                    upper = lower * 10;
            logger.trace("i: " + n);
            logger.trace("lower: " + lower);
            logger.trace("upper: " + upper);
            for (int i = lower; i < upper; i++) {
                String productString = "";
                for (int k = 1; k <= n; k++) {
                    productString += k * i;
                }
                long product = Long.parseLong(productString);
                Set<Integer> productDigits = Utils.getDigitsSet(product);
                if (productString.length() == 9
                        && productDigits.equals(digits)) {
                    logger.debug("found a solution for n = "
                            + n
                            + " and i = "
                            + i
                            + ": "
                            + productString);
                    if (product > maxProduct) {
                        result = productString;
                        maxProduct = product;
                    }
                }
            }
        }
        return result;
    }
}
