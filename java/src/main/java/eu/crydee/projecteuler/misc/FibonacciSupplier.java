package eu.crydee.projecteuler.misc;

import java.math.BigInteger;
import java.util.function.Supplier;

public class FibonacciSupplier implements Supplier<BigInteger> {
    
    private boolean start0 = true,
            start1 = true;
    
    private BigInteger previous,
            current;
    
    public FibonacciSupplier() {
        previous = BigInteger.ZERO;
        current = BigInteger.ONE;
    }
    
    public FibonacciSupplier(BigInteger start1, BigInteger start2) {
        previous = start1;
        current = start2;
    }
    
    @Override
    public BigInteger get() {
        if (start0) {
            start0 = false;
            return previous;
        } else if (start1) {
            start1 = false;
            return current;
        } else {
            BigInteger newCurrent = current.add(previous);
            previous = current;
            current = newCurrent;
            return newCurrent;
        }
    }
}
