package eu.crydee.projecteuler.problems._41to50;

import eu.crydee.projecteuler.problems.Problem;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class P43 implements Problem {
    
    private static final Logger logger
            = Logger.getLogger(P43.class.getCanonicalName());
    
    @Override
    public String getSolution() {
        int[] digits = new int[10];
        int[] divisors = {1, 2, 3, 5, 7, 11, 13};
        long total = 0;
        for (int i = 0; i < 1000; i += 17) {
            digits[9] = i % 10;
            digits[7] = i / 100;
            digits[8] = (i % 100) / 10;
            if (digits[9] == digits[8]
                    || digits[9] == digits[7]
                    || digits[8] == digits[7]) {
                continue;
            }
            total += pick(6, digits, divisors);
        }
        return String.valueOf(total);
    }
    
    private long pick(int digit, int[] digits, int[] divisors) {
        if (digit == -1) {
            logger.debug(ArrayUtils.toString(digits));
            return Long.parseLong(Arrays.stream(digits)
                    .boxed()
                    .map(String::valueOf)
                    .collect(Collectors.joining()));
        }
        long result = 0;
        candidate:
        for (int k = 0; k < 10; k++) {
            for (int l = digit + 1; l < 10; l++) {
                if (k == digits[l]) {
                    continue candidate;
                }
            }
            if ((k * 100 + digits[digit + 1] * 10 + digits[digit + 2])
                    % divisors[digit] > 0) {
                continue;
            }
            digits[digit] = k;
            result += pick(digit - 1, digits, divisors);
        }
        return result;
    }
}
