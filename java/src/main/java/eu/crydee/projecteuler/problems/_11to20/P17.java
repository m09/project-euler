package eu.crydee.projecteuler.problems._11to20;

import eu.crydee.projecteuler.problems.Problem;
import java.util.Arrays;
import org.apache.log4j.Logger;

public class P17 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P17.class.getCanonicalName());

    @Override
    public String getSolution() {
        int zero2nine = combinedSum("one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine"),
                ten2nineteen = combinedSum("ten", "eleven", "twelve",
                        "thirteen", "fourteen", "fifteen", "sixteen",
                        "seventeen", "eighteen", "nineteen"),
                tens = combinedSum("twenty", "thirty", "forty", "fifty",
                        "sixty", "seventy", "eighty", "ninety"),
                toNinetyNine = zero2nine * 9 + ten2nineteen + tens * 10;
        return String.valueOf(
                toNinetyNine * 10
                + zero2nine * 100
                + "hundred".length() * 900
                + "and".length() * 891
                + "onethousand".length());
    }

    private int combinedSum(String... strings) {
        return Arrays.stream(strings).mapToInt(s -> s.length()).sum();
    }
}
