package eu.crydee.projecteuler.primes;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;
import org.apache.log4j.Logger;

/**
 * Prime supplier that uses a Sieve of Eratosthenes with a very simple wheel.
 *
 * Details about those approaches can be found in the paper “The Genuine Sieve
 * of Eratosthenes” by Melissa E. O’Neill.
 */
public class PrimesSupplier implements Supplier<BigInteger> {

    private static final Logger logger
            = Logger.getLogger(PrimesSupplier.class.getCanonicalName());

    private boolean start0 = true,
            start1 = true,
            afterCurrent = true;

    private final TreeMap<BigInteger, Set<BigInteger>> crossed
            = new TreeMap<>();

    private BigInteger current = BigInteger.ONE;
    private final BigInteger four = BigInteger.valueOf(4),
            two = BigInteger.valueOf(2);

    @Override
    public BigInteger get() {
        if (start0) {
            start0 = false;
            return two;
        }
        if (start1) {
            start1 = false;
            return BigInteger.valueOf(3);
        }
        main:
        while (true) {
            current = current.add(afterCurrent ? four : two);
            afterCurrent = !afterCurrent;
            BigInteger first;
            while (!crossed.isEmpty()
                    && (first = crossed.firstKey()).compareTo(current) <= 0) {
                pop();
                if (first.equals(current)) {
                    continue main;
                }
            }
            final BigInteger toAdd = current.pow(2);
            add(toAdd, current);
            return current;
        }
    }

    private void pop() {
        Entry<BigInteger, Set<BigInteger>> entry = crossed.pollFirstEntry();
        final BigInteger from = entry.getKey();
        entry.getValue().forEach(prime -> {
            final BigInteger toAdd = from.add(prime);
            add(toAdd, prime);
        });
    }

    private void add(BigInteger index, BigInteger prime) {
        if (crossed.containsKey(index)) {
            crossed.get(index).add(prime);
        } else {
            Set<BigInteger> newSet = new HashSet<>();
            newSet.add(prime);
            crossed.put(index, newSet);
        }
    }
}
