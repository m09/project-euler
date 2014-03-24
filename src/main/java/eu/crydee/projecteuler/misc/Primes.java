package eu.crydee.projecteuler.misc;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.log4j.Logger;

public class Primes {

    private static final Logger logger
            = Logger.getLogger(Primes.class.getCanonicalName());

    public static List<Integer> getPrimesUpTo(int to) {
        List<Integer> result = new ArrayList<>();
        BitSet bits = new BitSet();
        bits.flip(0, to - 1);
        for (int i = bits.nextSetBit(0);
                i >= 0;
                i = bits.nextSetBit(i + 1)) {
            int current = i + 2;
            for (double j = (long) current * current;
                    j <= to;
                    j += current) {
                bits.clear((int) j - 2);
            }
        }
        for (int i = 0; i < to - 1; i++) {
            if (bits.get(i)) {
                result.add(i + 2);
            }
        }
        return result;
    }
}
