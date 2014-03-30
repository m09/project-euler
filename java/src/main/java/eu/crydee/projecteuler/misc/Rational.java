package eu.crydee.projecteuler.misc;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;

final public class Rational implements Comparable<Rational> {

    public static Rational ZERO = new Rational(0, 1),
            ONE = new Rational(1, 1);

    private long numerator, denominator;

    public Rational(long numerator, long denominator) {
        Preconditions.checkArgument(denominator != 0);
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    private void reduce() {
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        long gcd = LongMath.gcd(numerator, denominator);
        if (gcd != 1) {
            numerator /= gcd;
            denominator /= gcd;
        }
        reduced = true;
    }

    public Rational add(Rational o) {
        Preconditions.checkNotNull(o);
        try {
            long n = LongMath.checkedAdd(
                    LongMath.checkedMultiply(numerator, o.denominator),
                    LongMath.checkedMultiply(o.numerator, denominator));
            long d = LongMath.checkedMultiply(denominator, o.denominator);
            return new Rational(n, d);
        } catch (ArithmeticException ex) {
            return null;
        }
    }

    public Rational multiply(Rational o) {
        Preconditions.checkNotNull(o);
        try {
            return new Rational(
                    LongMath.checkedMultiply(numerator, o.numerator),
                    LongMath.checkedMultiply(denominator, o.denominator));
        } catch (ArithmeticException ex) {
            return null;
        }
    }

    public Rational divide(Rational o) {
        Preconditions.checkNotNull(o);
        return multiply(o.inverse());
    }

    public Rational inverse() {
        return new Rational(denominator, numerator);
    }

    public long getNumerator() {
        return denominator;
    }

    public long getDenominator() {
        return denominator;
    }

    public static Rational sum(Rational a, Rational b) {
        Preconditions.checkNotNull(a);
        return a.add(b);
    }

    public static Rational product(Rational a, Rational b) {
        Preconditions.checkNotNull(a);
        return a.multiply(b);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (this.numerator ^ (this.numerator >>> 32));
        hash = 17 * hash + (int) (this.denominator ^ (this.denominator >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rational other = (Rational) obj;
        if (getNumerator() != other.getNumerator()) {
            return false;
        }
        return getDenominator() == other.getDenominator();
    }

    @Override
    public String toString() {
        return numerator + "%" + denominator;
    }

    @Override
    public int compareTo(Rational o) {
        if (equals(o)) {
            return 0;
        }
        return (double) numerator / denominator
                > (double) o.numerator / o.denominator
                ? 1 : -1;
    }

}
