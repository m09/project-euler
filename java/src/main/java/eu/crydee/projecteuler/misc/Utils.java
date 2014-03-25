package eu.crydee.projecteuler.misc;

import eu.crydee.projecteuler.problems.Problem;
import java.io.InputStream;
import org.apache.log4j.Logger;

public class Utils {

    private static final Logger logger
            = Logger.getLogger(Utils.class.getCanonicalName());

    static public char[] splitNumberIntoDigits(Number n) {
        return n.toString().toCharArray();
    }

    static public <T extends Problem> InputStream getData(Class<T> c) {
        return Utils.class.getResourceAsStream(
                "/eu/crydee/projecteuler/problems/"
                + c.getSimpleName()
                + ".data");
    }
}
