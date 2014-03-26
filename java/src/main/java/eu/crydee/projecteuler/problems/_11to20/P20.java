package eu.crydee.projecteuler.problems._11to20;

import com.google.common.math.BigIntegerMath;
import eu.crydee.projecteuler.problems.Problem;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class P20 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P20.class.getCanonicalName());

    @Override
    public String getSolution() {
        return String.valueOf(Arrays.stream(ArrayUtils.toObject(
                BigIntegerMath.factorial(100).toString().toCharArray()))
                .map(String::valueOf)
                .mapToInt(Integer::parseInt)
                .sum());
    }
}
