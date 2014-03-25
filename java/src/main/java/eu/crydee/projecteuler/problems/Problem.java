package eu.crydee.projecteuler.problems;

/**
 * Interface used to access each problem in a consistent manner.
 *
 * @author Hugo “m09” Mougard
 */
public interface Problem {

    /**
     * Method to run the solving of the problem.
     *
     * @return the solution as it should be entered on the website.
     */
    public String getSolution();
}
