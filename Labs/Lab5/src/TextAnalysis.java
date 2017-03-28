import java.util.Collection;
/**
 * Main program
 * @author James Heliotis
 */
public class TextAnalysis {

    /**
     * Get a file name from the command line and ask a TextProcessor
     * to analyze it.
     *
     * @param args a single-element array containing the file name
     */
    public static void main( String[] args ) {
        if ( args.length != 1 ) {
            System.err.println( "Usage: java TextProcessor file" );
            System.exit( 2 );
        }
        TextProcessor textProc = new TextProcessorImpl( args[ 0 ] );

        Collection< String > longestWords = textProc.getLongestWords();
        System.out.println( "Longest words: " + longestWords );

        Collection< String > shortestWords = textProc.getShortestWords();
        System.out.println( "Shortest words: " + shortestWords );

        System.out.println(
                "Most common word of length 5: " +
                        textProc.mostCommon( 5 )
        );

        System.out.println(
                "Most common first character: " +
                        textProc.mostCommonFirstWeighted()
        );

        System.out.println(
                "Most common first character, ignoring word frequency: " +
                        textProc.mostCommonFirstUnweighted()
        );

    }
}
