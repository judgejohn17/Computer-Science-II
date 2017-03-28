import java.util.Collection;

/**
 * Interface for classes that read in a literary work and analyze word use.
 *
 * (For specifications of the constructor for those classes, see the
 * laboratory assignment writeup.)
 *
 * @author James Heliotis
 */
public interface TextProcessor {
    /**
     * Return the letters that are most likely to appear at
     * the start of a word from the literary work. Frequency
     * of the words involved is not taken into account. There
     * will often only be one letter, but in case of a tie,
     * a collection is returned.
     * @return the most common first letter(s) in the words
     */
    Collection<Character> mostCommonFirstUnweighted();

    /**
     * Return the longest word or words from the literary work.
     * There will often only be one word, but in case of a tie,
     * a collection is returned.
     * @return the longest word(s)
     */
    Collection< String > getLongestWords();

    /**
     * Return the shortest word or words from the literary work.
     * Since most languages contain many 1-letter words,
     * a collection is returned.
     * @return the shortest word(s)
     */
    Collection< String > getShortestWords();

    /**
     * Return the most common word or words of a given
     * length in the literary work. There will often
     * only be one such word, but in case of a tie,
     * a collection is returned.
     * @param length the length of words to consider
     * @return the list of most common words
     */
    Collection< String > mostCommon(int length);

    /**
     * Return the letters that are most likely to appear at
     * the start of a word from the literary work. Frequency
     * of the words involved is taken into account. There
     * will often only be one letter, but in case of a tie,
     * a collection is returned.
     * @return the most common first letter(s) in the words
     */
    Collection< Character > mostCommonFirstWeighted();
}