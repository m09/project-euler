package eu.crydee.projecteuler.misc;

import com.google.common.primitives.Ints;
import java.util.Comparator;

public class NumericStringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Ints.compare(Integer.parseInt(o1), Integer.parseInt(o2));
    }

}
