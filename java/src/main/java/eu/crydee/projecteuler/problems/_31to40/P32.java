package eu.crydee.projecteuler.problems._31to40;

import com.google.common.collect.Sets;
import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.log4j.Logger;

public class P32 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P32.class.getCanonicalName());

    @Override
    public String getSolution() {
        List<Set<Integer>> indicesList = IntStream.range(0, 5).boxed()
                .map(i -> IntStream.range(0, 9 - i).boxed()
                        .collect(Collectors.toSet()))
                .collect(Collectors.toList());
        List<Integer> digitsPool = IntStream.range(1, 10).boxed()
                .collect(Collectors.toList());

        Set<Integer> result = new HashSet<>();

        for (List<Integer> indices : Sets.cartesianProduct(indicesList)) {
            List<Integer> pool = new ArrayList<>(digitsPool);
            int[] d = new int[5];
            for (int i = 0; i < indices.size(); i++) {
                d[i] = pool.get(indices.get(i));
                pool.remove((int) indices.get(i));
            }
            Set<Integer> digitsLeft = new HashSet<>(pool);
            int a1 = d[0] * 10 + d[1],
                    a2 = d[0],
                    b1 = d[2] * 100 + d[3] * 10 + d[4],
                    b2 = d[1] * 1000 + d[2] * 100 + d[3] * 10 + d[4],
                    p1 = a1 * b1,
                    p2 = a2 * b2;
            List<Integer> d1 = Utils.getDigitsList(p1),
                    d2 = Utils.getDigitsList(p2);
            Set<Integer> s1 = new HashSet<>(d1),
                    s2 = new HashSet<>(d2);
            if (d1.size() == s1.size() && s1.equals(digitsLeft)) {
                result.add(p1);
            }
            if (d2.size() == s2.size() && s2.equals(digitsLeft)) {
                result.add(p2);
            }
        }

        return result.stream().reduce(0, Integer::sum).toString();
    }
}
