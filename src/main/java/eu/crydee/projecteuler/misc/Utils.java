package eu.crydee.projecteuler.misc;

import java.io.InputStream;
import org.apache.log4j.Logger;

public class Utils {

    private static final Logger logger
            = Logger.getLogger(Utils.class.getCanonicalName());

    static public char[] splitNumberIntoDigits(Number n) {
        return n.toString().toCharArray();
    }

    static public InputStream getData(int problem) {
        return Utils.class.getResourceAsStream(
                "/eu/crydee/projecteuler/problems/P"
                + String.valueOf(problem)
                + ".data");
    }
}
