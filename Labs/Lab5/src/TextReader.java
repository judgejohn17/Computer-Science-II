import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Convenience class for programmers who don't know file
 * input/output in Java. ... yet
 */
public class TextReader {
    /**
     * Produce a Scanner instance connected to the given file.
     * If the file cannot be opened, this method will report
     * it on standard error, and then terminate the program
     * with a status of 1.
     * @param fileName the name of the file to be scanned
     * @return a Scanner instance on the file. (Please close it when done!)
     */
    public static Scanner openFile( String fileName ) {
        Scanner result = null;
        try {
            InputStream istream = new FileInputStream( fileName );
            result = new Scanner( istream );
        }
        catch ( FileNotFoundException fnfe ) {
            System.err.println( fnfe );
            System.err.println( "File \"" + fileName + "\" does not exist." );
            System.exit( 1 );
        }
        return result;
    }
}

