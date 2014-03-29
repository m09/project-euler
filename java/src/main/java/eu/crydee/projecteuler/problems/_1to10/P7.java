package eu.crydee.projecteuler.problems._1to10;

import eu.crydee.projecteuler.misc.Primes;
import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P7 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P7.class.getCanonicalName());

    @Override
    public String getSolution() {
        return Primes.stream().skip(10000).findFirst().get().toString();
    }
}
