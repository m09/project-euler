package eu.crydee.projecteuler.problems._31to40;

import eu.crydee.projecteuler.problems.Problem;
import org.apache.log4j.Logger;

public class P31 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P31.class.getCanonicalName());

    @Override
    public String getSolution() {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        return String.valueOf(getWays(coins, coins.length - 1, 200));
    }

    private int getWays(int[] coins, int c, int amount) {
        if (c == 0) {
            return 1;
        }
        int result = 0;
        for (int current = amount; current >= 0; current -= coins[c]) {
            result += getWays(coins, c - 1, current);
        }
        return result;
    }
}
