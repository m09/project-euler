package eu.crydee.projecteuler.problems._41to50;

import eu.crydee.projecteuler.misc.Utils;
import eu.crydee.projecteuler.problems.Problem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class P42 implements Problem {

    private static final Logger logger
            = Logger.getLogger(P42.class.getCanonicalName());

    @Override
    public String getSolution() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Utils.getData(P42.class)))) {
            String content = br.readLine();
            int total = 0;
            Set<Integer> triangles = new HashSet<>();
            for (int i = 2, next = 1; i < 1000; i++) {
                triangles.add(next);
                next += i;
            }
            if (content == null) {
                return "empty input file";
            }
            for (String wordQuoted : content.split(",")) {
                String word = wordQuoted.substring(1, wordQuoted.length() - 1);
                int score = Arrays.stream(
                        ArrayUtils.toObject(word.toCharArray()))
                        .mapToInt(c -> Character.codePointAt(new char[]{c}, 0))
                        .map(c -> c - 64)
                        .sum();
                if (triangles.contains(score)) {
                    total++;
                }
            }
            return String.valueOf(total);
        } catch (IOException ex) {
            return "ioexception: " + ex.getLocalizedMessage();
        }
    }
}
