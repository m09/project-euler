package eu.crydee.projecteuler.problems;

import eu.crydee.projecteuler.misc.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

public class P67 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P67.class.getCanonicalName());

    @Override
    public String getSolution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Utils.getData(P67.class)))) {
            String line;
            List<List<Integer>> input = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                List<Integer> row = new ArrayList<>();
                Arrays.stream(line.split(" ")).forEach(
                        s -> row.add(Integer.parseInt(s)));
                input.add(row);
            }
            for (int i = input.size() - 2; i >= 0; i--) {
                int ncol = input.get(i).size();
                List<Integer> row = input.get(i),
                        nextRow = input.get(i + 1);
                for (int j = 0; j < ncol; j++) {
                    input.get(i).set(j, row.get(j)
                            + max(nextRow.get(j), nextRow.get(j + 1)));
                }
            }
            return input.get(0).get(0).toString();
        } catch (IOException ex) {
            return "ioexception: " + ex.getLocalizedMessage();
        }
    }
}
