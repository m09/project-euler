package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.misc.Divisors;
import eu.crydee.projecteuler.problems.Problem;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.log4j.Logger;

public class P23 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P23.class.getCanonicalName());

    @Override
    public String getSolution() {
        List<Integer> abundants = IntStream.range(1, 28123)
                .filter(n -> Divisors.sumProperDivisors(n) > n)
                .boxed()
                .collect(Collectors.toList());
        BitSet summables = new BitSet(28123);
        abundants.forEach(abundant1
                -> abundants.stream()
                .filter(a -> abundant1 + a < summables.size())
                .forEach(abundant2
                        -> summables.set(abundant1 + abundant2)));
        summables.flip(0, summables.length());
        return String.valueOf(summables.stream().sum());
    }
}
