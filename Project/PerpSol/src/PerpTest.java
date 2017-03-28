import perp.machine.stu.Machine;
import perp.tree.stu.ParseTree;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Run a test of the perp language system.
 *
 * @author James Heliotis
 */
public class PerpTest {

    private static List< List< String > > programs = Arrays.asList(
            new LinkedList<>( Arrays.asList( ":=", "x", "55" ) ),
            new LinkedList<>( Arrays.asList(
                    ":=", "able", "77",
                    ":=", "baker", "3",
                    ":=", "charlie", "//", "able", "baker"
            ) ),
            new LinkedList<>( Arrays.asList(
                    ":=", "a", "3",
                    ":=", "b", "4",
                    ":=", "c", "5",
                    ":=", "result", "+", "*", "b", "b", "_", "*", "*", "4", "a", "c"
            ) ),
            new LinkedList<>( Arrays.asList(
                    ":=", "x", "1",
                    ":=", "x", "+", "x", "x",
                    ":=", "x", "*", "x", "x",
                    ":=", "x", "-", "2", "_", "x",
                    "@", "x",
                    ":=", "x", "//", "x", "2",
                    ":=", "pastafagiole", "#", "+", "19", "x"
            ) ),
            new LinkedList<>( Arrays.asList(
                    ":=", "a", "1",
                    ":=", "b", "_", "1",
                    ":=", "c", "_", "6",
                    ":=", "root", "//", "+", "_", "b", "#","-", "*",
                          "b", "b", "*", "*", "4", "a", "c", "*", "2", "a",
                    ":=", "root2", "//", "-", "_", "b", "#", "-", "*",
                          "b", "b", "*", "*", "4", "a", "c", "*", "2", "a"
            ) )
    );

    public static int NUM_TESTS = programs.size();

    /**
     * Run a test on the Perp programming system
     * @param args if numeric and the number is less than the number of
     *             private stored tests, run the private test corresponding
     *             to that number; if other args, consider them tokens
     *             of a Perp program and run tests on that program;
     *             if no arguments, read the source program from standard
     *             input.
     */


    public static void main( String[] args ) {
        List< String > tokenList;

        if ( args.length == 0 ) {
            tokenList = new LinkedList<>();
            try ( Scanner text = new Scanner( System.in ) ) {
                System.out.print("PERP> ");
                while ( text.hasNextLine() ) {
                    String line = text.nextLine();
                    if ( line.equals( "." ) ) break; // For IntelliJ console
                    tokenList.addAll(Arrays.asList(line.split(" ")));
                    System.out.print("PERP> ");
                }
            }
        }
        else if ( args.length == 1 ) {
            int testNum = -1;
            try {
                testNum = Integer.parseInt( args[0] );
            }
            catch (NumberFormatException nfe) {
                System.err.println( "Illegal test number \"" + args[0] + '"' );
                System.exit( 1 );
            }
            if (testNum < 0 || testNum >= NUM_TESTS) {
                System.err.println("Test number out of range:" + args[0] );
                System.exit( 1 );
            }

            tokenList = programs.get( testNum );
        }
        else {
            tokenList = new LinkedList<>( Arrays.asList( args ) );
        }

        ParseTree tree = new ParseTree( tokenList );

        tree.displayProgram();

        tree.interpret();

        List< Machine.Instruction > program = tree.compile();

        Machine.displayInstructions( program );

        Machine.execute( program );
    }

}