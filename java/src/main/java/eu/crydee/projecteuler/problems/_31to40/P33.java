package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.misc.Rational;
import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P33 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P33.class.getCanonicalName());

    @Override
    public String getSolution() {
        Rational result = new Rational(1, 1);
        for (long i = 11; i < 99; i++) {
            if (i % 10 == 0) {
                continue;
            }
            for (long j = i + 1; j < 100; j++) {
                if (j % 10 == 0) {
                    continue;
                }
                long newI1 = i % 10,
                        newJ1 = j / 10,
                        newI2 = i / 10,
                        newJ2 = j % 10;
                Rational o = new Rational(i, j),
                        o1 = new Rational(newI1, newJ1),
                        o2 = new Rational(newI2, newJ2);
                if (newI1 == newJ1
                        && o2.compareTo(Rational.ONE) < 0
                        && o.equals(o2)) {
                    result = result.multiply(o);
                }
                if (newI2 == newJ2
                        && o1.compareTo(Rational.ONE) < 0
                        && o.equals(o1)) {
                    result = result.multiply(o);
                }
            }
        }
        return String.valueOf(result.getDenominator());
    }
}
