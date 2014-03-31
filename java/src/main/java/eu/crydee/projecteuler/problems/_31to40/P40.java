package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.problems.Problem;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class P40 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P40.class.getCanonicalName());

    @Override
    public String getSolution() {
        final Set<Integer> indices = new HashSet<>();
        indices.add(1);
        indices.add(10);
        indices.add(100);
        indices.add(1000);
        indices.add(10000);
        indices.add(100000);
        indices.add(1000000);
        final AtomicInteger index = new AtomicInteger(0);
        return String.valueOf(IntStream.rangeClosed(1, 1000000).boxed()
                .map(String::valueOf)
                .flatMap(n -> Arrays.stream(
                                ArrayUtils.toObject(n.toCharArray())))
                .map(String::valueOf)
                .mapToInt(Integer::parseInt)
                .peek(n -> index.incrementAndGet())
                .filter(n -> indices.contains(index.get()))
                .reduce(1, (a, b) -> a * b));

    }
}
