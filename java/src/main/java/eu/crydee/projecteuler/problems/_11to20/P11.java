package eu.crydee.projecteuler.problems._11to20;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class P11 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P11.class.getCanonicalName());

    @Override
    public String getSolution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Utils.getData(P11.class)))) {
            List<List<Integer>> input = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> numbers = new ArrayList<>();
                for (String numberString : line.split(" ")) {
                    numbers.add(Integer.parseInt(numberString));
                }
                input.add(numbers);
            }
            int max = Integer.MIN_VALUE, product;
            // horizontally
            for (List<Integer> row : input) {
                Deque<Integer> window = new LinkedList<>();
                for (Integer cell : row) {
                    if (window.size() == 4) {
                        window.pop();
                    }
                    window.add(cell);
                    if ((product = getProduct(window)) > max) {
                        max = product;
                    }
                }
            }
            // diagonally NW to SE
            int nrow = input.size(), ncol = input.get(0).size();
            for (int r = 0; r < nrow - 3; r++) {
                for (int c = 0; c < ncol - 3; c++) {
                    if ((product = getProduct(Arrays.asList(
                            input.get(r).get(c),
                            input.get(r + 1).get(c + 1),
                            input.get(r + 2).get(c + 2),
                            input.get(r + 3).get(c + 3)))) > max)  {
                        max = product;
                    }
                }
            }
            // diagonally NE to SW
            for (int r = 0; r < nrow - 3; r++) {
                for (int c = 0; c < ncol - 3; c++) {
                    if ((product = getProduct(Arrays.asList(
                            input.get(r).get(c + 3),
                            input.get(r + 1).get(c + 2),
                            input.get(r + 2).get(c + 1),
                            input.get(r + 3).get(c)))) > max)  {
                        max = product;
                    }
                }
            }
            // vertically
            for (int r = 0; r < nrow - 3; r++) {
                for (int c = 0; c < ncol; c++) {
                    if ((product = getProduct(Arrays.asList(
                            input.get(r).get(c),
                            input.get(r + 1).get(c),
                            input.get(r + 2).get(c),
                            input.get(r + 3).get(c)))) > max)  {
                        max = product;
                    }
                }
            }
            return String.valueOf(max);
        } catch (IOException ex) {
            return "ioexception: " + ex.getLocalizedMessage();
        }
    }

    private int getProduct(Collection<Integer> window) {
        return window.stream().reduce(1, (a, b) -> a * b, (a, b) -> a * b);
    }
}
