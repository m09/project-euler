package eu.crydee.projecteuler.problems._21to30;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

public class P22 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P22.class.getCanonicalName());

    @Override
    public String getSolution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Utils.getData(P22.class)))) {
            String line = br.readLine();
            if (line == null) {
                return "empty data file";
            }
            long index = 0l,
                    total = 0l;
            List<String> words = Arrays.asList(line.split(","));
            words.sort(null);
            for (String word : words) {
                index++;
                String parsed = word.substring(1, word.length() - 1);
                char[] chars = parsed.toCharArray();
                long score = 0;
                for (int i = 0; i < chars.length; i++) {
                    score += Character.codePointAt(chars, i) - 64;
                }
                total += score * index;
            }
            return String.valueOf(total);
        } catch (IOException ex) {
            return "ioexception: " + ex.getLocalizedMessage();
        }
    }
}
