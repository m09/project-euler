package eu.crydee.projecteuler.misc;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Fibonacci {

    static public Stream<BigInteger> stream() {
        return Stream.generate(new FibonacciSupplier());
    }
}
