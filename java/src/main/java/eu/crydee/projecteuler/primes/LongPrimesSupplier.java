package eu.crydee.projecteuler.primes;

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
public class LongPrimesSupplier implements Supplier<Long> {

    private static final Logger logger
            = Logger.getLogger(LongPrimesSupplier.class.getCanonicalName());

    private boolean start0 = true,
            start1 = true,
            afterCurrent = true;

    private final TreeMap<Long, Set<Long>> crossed
            = new TreeMap<>();

    private Long current = 1l;

    @Override
    public Long get() {
        if (start0) {
            start0 = false;
            return 2l;
        }
        if (start1) {
            start1 = false;
            return 3l;
        }
        main:
        while (true) {
            current += afterCurrent ? 4l : 2l;
            afterCurrent = !afterCurrent;
            Long first;
            while (!crossed.isEmpty()
                    && (first = crossed.firstKey()) <= current) {
                pop();
                if (first.equals(current)) {
                    continue main;
                }
            }
            Long toAdd = current * current;
            add(toAdd, current);
            return current;
        }
    }

    private void pop() {
        Entry<Long, Set<Long>> entry = crossed.pollFirstEntry();
        final Long from = entry.getKey();
        entry.getValue().forEach(prime -> add(from + prime, prime));
    }

    private void add(Long index, Long prime) {
        if (crossed.containsKey(index)) {
            crossed.get(index).add(prime);
        } else {
            Set<Long> newSet = new HashSet<>();
            newSet.add(prime);
            crossed.put(index, newSet);
        }
    }
}
