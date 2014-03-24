package eu.crydee.projecteuler.problems;

import eu.crydee.projecteuler.misc.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

public class P13 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P13.class.getCanonicalName());

    @Override
    public String getSolution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Utils.getData(P13.class)))) {
            List<BigInteger> input = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                input.add(new BigInteger(line));
            }
            BigInteger sum = input.stream().reduce(
                    BigInteger.ZERO,
                    (r, i) -> r.add(i),
                    (a, b) -> a.add(b));
            char[] chars = Arrays.copyOfRange(
                    sum.toString().toCharArray(),
                    0,
                    10);
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(c);
            }
            return sb.toString();
        } catch (IOException ex) {
            return "ioexception: " + ex.getLocalizedMessage();
        } catch (NumberFormatException ex) {
            return "number format exception: " + ex.getLocalizedMessage();
        }
    }
}
