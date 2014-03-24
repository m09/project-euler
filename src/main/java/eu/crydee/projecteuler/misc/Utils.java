package eu.crydee.projecteuler.misc;

import org.apache.log4j.Logger;

public class Utils {

    private static final Logger logger
            = Logger.getLogger(Utils.class.getCanonicalName());

    static public char[] splitNumberIntoDigits(Number n) {
        return n.toString().toCharArray();
    }
}
