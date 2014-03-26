package eu.crydee.projecteuler.problems._11to20;

import eu.crydee.projecteuler.problems.Problem;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

public class P19 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P19.class.getCanonicalName());

    private class MonthDate {

        public final int month;
        public final int year;

        public MonthDate(int month, int year) {
            this.month = month;
            this.year = year;
        }
    }

    @Override
    public String getSolution() {

        return Stream.iterate(new MonthDate(1, 1901), this::nextMonth)
                .limit(100 * 12)
                .map(m -> nDays(m))
                .reduce(
                        Pair.of(0, 2),
                        (u, n) -> {
                            return Pair.of(
                                    u.getLeft() + (u.getRight() == 0 ? 1 : 0),
                                    (u.getRight() + n) % 7);
                        },
                        (u, v) -> Pair.of(u.getKey() + v.getKey(),
                                (u.getRight() + v.getRight()) % 7))
                .getKey()
                .toString();
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    private int nDays(MonthDate monthDate) {
        if (monthDate.month == 2) {
            return isLeapYear(monthDate.year) ? 29 : 28;
        }
        return 31 - (monthDate.month - 1) % 7 % 2;
    }

    private MonthDate nextMonth(MonthDate monthDate) {
        if (monthDate.month == 12) {
            return new MonthDate(1, monthDate.year + 1);
        }
        return new MonthDate(monthDate.month + 1, monthDate.year);
    }
}
