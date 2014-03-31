package eu.crydee.projecteuler.misc;

import eu.crydee.projecteuler.problems.Problem;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class Utils {

    private static final Logger logger
            = Logger.getLogger(Utils.class.getCanonicalName());

    static public char[] splitNumberIntoDigits(Number n) {
        return n.toString().toCharArray();
    }

    static public List<Integer> getDigitsList(long n) {
        return Arrays.stream(
                ArrayUtils.toObject(String.valueOf(n).toCharArray()))
                .map(String::valueOf)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static public Set<Integer> getDigitsSet(long n) {
        return Arrays.stream(
                ArrayUtils.toObject(String.valueOf(n).toCharArray()))
                .map(String::valueOf)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    static public Deque<Integer> getDigitsStack(long n) {
        Deque<Integer> result = new LinkedList<>();
        char[] chars = String.valueOf(n).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            result.push(Integer.parseInt(String.valueOf(chars[i])));
        }
        return result;
    }

    static public int numberOfDigits(Number n) {
        return n.toString().length();
    }

    static public <T extends Problem> InputStream getData(Class<T> c) {
        return Utils.class.getResourceAsStream(
                "/"
                + c.getCanonicalName().replace('.', '/')
                + ".data");
    }
}
