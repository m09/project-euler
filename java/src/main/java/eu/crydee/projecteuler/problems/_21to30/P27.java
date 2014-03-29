package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.primes.Primes;
import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P27 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P27.class.getCanonicalName());

    @Override
    public String getSolution() {
        int max = Integer.MIN_VALUE;
        long maxA = -1001;
        long maxB = -1001;
        for (long a = -999; a < 1000; a++) {
            for (long b = -999; b < 1000; b++) {
                long n = 0l;
                int steps = 0;
                while (Primes.isPrime(n * n + a * n + b)) {
                    steps++;
                    n++;
                }
                if (steps > max) {
                    max = steps;
                    maxA = a;
                    maxB = b;
                }
            }
        }
        return String.valueOf(maxA * maxB);
    }
}
