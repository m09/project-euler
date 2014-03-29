package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.misc.Fibonacci;
import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;

public class P25 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P25.class.getCanonicalName());

    @Override
    public String getSolution() {
        AtomicInteger index = new AtomicInteger(-1);
        Fibonacci.stream()
                .peek(n -> index.incrementAndGet())
                .filter(n -> Utils.numberOfDigits(n) >= 1000)
                .findFirst();
        return String.valueOf(index.get());
    }

    private <T extends Comparable<T>> List<T> getNext(List<T> permutation) {
        if (permutation == null) {
            return null;
        }
        if (permutation.size() < 2) {
            return permutation;
        }
        List<T> result = new ArrayList<>(permutation);
        T first = result.get(result.size() - 1);
        List<T> previous = new ArrayList<>();
        previous.add(first);
        for (int i = result.size() - 2; i >= 0; i--) {
            T current = result.get(i),
                    toSwap;
            if ((toSwap = selectMinAboveElem(current, previous)) != null) {
                for (int j = result.size() - 1; j > i; j--) {
                    result.remove(j);
                }
                result.set(i, toSwap);
                previous.remove(toSwap);
                previous.add(current);
                previous.sort(null);
                result.addAll(i + 1, previous);
                return result;
            }
            previous.add(result.get(i));
        }
        return null;
    }

    private <T extends Comparable<T>> T selectMinAboveElem(
            T t,
            Collection<T> c) {
        T min = null;
        for (T item : c) {
            if (item.compareTo(t) > 0
                    && (min != null && item.compareTo(min) < 0
                    || min == null)) {
                min = item;
            }
        }
        return min;
    }
}
