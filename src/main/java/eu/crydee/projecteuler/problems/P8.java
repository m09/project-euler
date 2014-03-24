package eu.crydee.projecteuler.problems;

import eu.crydee.projecteuler.misc.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import org.apache.log4j.Logger;

public class P8 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P8.class.getCanonicalName());

    @Override
    public String getSolution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Utils.getData(8)))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            char[] inputChars = sb.toString().toCharArray();
            Deque<Integer> window = new LinkedList<>();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < inputChars.length; i++) {
                if (window.size() == 5) {
                    window.pop();
                }
                window.add(Integer.parseInt(String.valueOf(inputChars[i])));
                int product = window.stream()
                        .reduce(1, (a, b) -> a * b, (a, b) -> a * b);
                if (product > max) {
                    max = product;
                }
            }
            return String.valueOf(max);
        } catch (IOException ex) {
            return "ioexception: " + ex.getLocalizedMessage();
        }
    }

}
