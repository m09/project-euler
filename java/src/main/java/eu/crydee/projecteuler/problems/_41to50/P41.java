package eu.crydee.projecteuler.problems._41to50;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.primes.Primes;
import eu.crydee.projecteuler.problems.Problem;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.log4j.Logger;

public class P41 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P41.class.getCanonicalName());

    @Override
    public String getSolution() {
        
        List<Set<Integer>> pools = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            pools.add(IntStream.rangeClosed(1, i + 1).boxed()
                    .collect(Collectors.toSet()));
        }
        
        List<Long> primes = Primes.getPrimesUpTo(7654321l);
        for (int i = primes.size() - 1; i >= 0; i--) {
            long prime = primes.get(i);
            List<Integer> digits = Utils.getDigitsList(prime);
            Set<Integer> digitsSet = Utils.getDigitsSet(prime);
            if (digits.size() == digitsSet.size()
                    && digitsSet.equals(pools.get(digits.size() - 1))) {
                return String.valueOf(prime);
            }
        }
        return "none";
    }
}