for ((i=11; i < 21; i++)); do
    content='package eu.crydee.projecteuler.problems._11to20;

import org.junit.Test;
import org.junit.Assert;

public class P'$i'Test {

    @Test
    public void testGetSolution() {
        Assert.assertEquals("'$i'", new P'$i'().getSolution());
    }
}'
    echo "$content" > _11to20/P"$i"Test.java
done

