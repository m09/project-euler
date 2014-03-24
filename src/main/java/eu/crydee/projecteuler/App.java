package eu.crydee.projecteuler;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import eu.crydee.projecteuler.problems.Problem;
import eu.crydee.projecteuler.misc.NumericStringComparator;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public class App {

    private static final Logger logger
            = Logger.getLogger(App.class.getCanonicalName());
    private static final Map<String, Class<? extends Problem>> problems
            = new HashMap<>();

    static {
        try {
            ClassPath classPath
                    = ClassPath.from(ClassLoader.getSystemClassLoader());
            ImmutableSet<ClassInfo> classInfos
                    = classPath.getTopLevelClassesRecursive(
                            "eu.crydee.projecteuler.problems");
            classInfos.forEach(classInfo -> {
                Class<?> c = classInfo.load();
                if (Problem.class.isAssignableFrom(c)
                        && !Modifier.isAbstract(c.getModifiers())
                        && !c.isInterface()) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Problem> c2 = (Class<? extends Problem>) c;
                    problems.put(
                            classInfo.getName().replaceFirst(
                                    "eu.crydee.projecteuler.problems.P", ""),
                            c2);
                }
            });
        } catch (IOException ex) {
            logger.error("could not retrieve class loader, exact message is: "
                    + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("h", "help", false, "print this message");
        options.addOption(OptionBuilder
                .withLongOpt("problem")
                .hasArg()
                .withArgName("problem_number")
                .withDescription("problem to run")
                .create("p"));
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("project-euler", options);
                System.exit(0);
            }
            List<String> toRun = new ArrayList<>();
            if (cmd.hasOption('p')) {
                int number = Integer.parseInt(cmd.getOptionValue('p'));
                String problemName = String.valueOf(number);
                if (!problems.containsKey(problemName)) {
                    throw new ParseException("Can't find the problem class. "
                            + "Please try again.");
                }
                toRun.add(problemName);
            } else {
                toRun.addAll(problems.keySet());
            }
            toRun.sort(new NumericStringComparator());
            for (String problemName : toRun) {
                Problem problem = problems.get(problemName)
                        .newInstance();
                logger.info("Running problem " + problemName);
                logger.info("Solution is: " + problem.getSolution());
            }
        } catch (ParseException ex) {
            System.err.println("The CLI args could not be parsed.");
            System.err.println("The error message was:");
            System.err.println(" " + ex.getMessage());
            System.exit(1);
        } catch (IllegalAccessException | InstantiationException ex) {
            System.err.println("Could not create an instance of the problem:");
            System.err.println(" " + ex.getMessage());
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.err.println("Could not understand -p as a number:");
            System.err.println(" " + ex.getMessage());
            System.exit(1);
        }
    }
}
