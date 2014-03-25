package eu.crydee.projecteuler.problems;

import java.math.BigInteger;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class P16 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P16.class.getCanonicalName());

    @Override
    public String getSolution() {
        char[] chars = BigInteger.valueOf(2).pow(1000).toString().toCharArray();
        return String.valueOf(Arrays.stream(ArrayUtils.toObject(chars))
                .mapToInt(a -> Integer.parseInt(a.toString()))
                .sum());
    }
}
