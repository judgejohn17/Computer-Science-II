/**
 * Created by johnjudge on 2/25/15.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

public class TextProcessorImpl implements TextProcessor {

    private String filename;
    private ArrayList<String> lst = new ArrayList<String>();

    public TextProcessorImpl(String filename) {
        this.filename = filename;
        String current;
        Scanner scan = TextReader.openFile(filename);
        while (scan.hasNext()) {
            current = scan.next();
            if (current.matches(".*[0-9].*")) {

            } else {
                current = current.replaceAll("\\p{Punct}+", "");
                if (current.isEmpty()) {
                } else {
                    lst.add(current);
                }
            }
        }
    }
    /**
     * Return the longest word or words from the literary work.
     * There will often only be one word, but in case of a tie,
     * a collection is returned.
     * @return the longest word(s)
     */
    @Override
    public Collection<String> getLongestWords() {

        String longestWord = "";
        String current;
        Scanner scan = TextReader.openFile(filename);
        // Create scanner
        ArrayList<String> lst = new ArrayList<String>();
        //Create new array list
        while (scan.hasNext()) {
        //make sure the text file has a more than one word in it
            current = scan.next();
            //set current to the next word
            if (current.length() > longestWord.length()) {
            //if the current word length is greater than the longest word length
                longestWord = current;
                //set the new longest word to current
                lst.clear();
                //clear the previous array
                lst.add(longestWord);
                //add the new longest word

            } else if (current.length() == longestWord.length()) {
            //else if the current word length = the longest word length
                if (!lst.contains(current)) {
                    lst.add(current);
                    //add the current word to the array
                }
            }


        }
        return lst;

    }
    /**
     * Return the shortest word or words from the literary work.
     * Since most languages contain many 1-letter words,
     * a collection is returned.
     * @return the shortest word(s)
     */
    @Override
    public Collection<String> getShortestWords(){
        String current;
        int shortestWord = 999999999;
        String newWord;
        //Create Scanner
        Scanner scan = TextReader.openFile(filename);
        //Create new array list
        ArrayList<String> lst1 = new ArrayList<String>();
        while (scan.hasNext()) {
        //while the text has a next word in it
            current = scan.next();
            //set current to that next word
            if (current.length() < shortestWord) {
            //if the current word length is greater than the longest word length
                newWord = current;
                //set the new longest word to current
                lst1.clear();
                //clear the previous array
                lst1.add(newWord);
                //add the new longest word

            } else if (current.length() == shortestWord) {
            //else if the current word length = the longest word length
                if (!lst1.contains(current)) {
                    lst1.add(current);
                    //add the current word to the array
                }
            }


        }
        return lst;
    }
    /**
     * Return the letters that are most likely to appear at
     * the start of a word from the literary work. Frequency
     * of the words involved is not taken into account. There
     * will often only be one letter, but in case of a tie,
     * a collection is returned.
     * @return the most common first letter(s) in the words
     */
    @Override
    public Collection<Character> mostCommonFirstUnweighted(){
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        for (String s : lst) {
            if (hashmap.get(s.charAt(0)) == null) {
                hashmap.put(s.charAt(0), 1);
            } else {
                hashmap.put(s.charAt(0), hashmap.get(s.charAt(0)).intValue() + 1);
            }
        }
        Collection<Integer> values = hashmap.values(); // get values
        List<Integer> listVal = new ArrayList<Integer>(values);
        Collections.sort(listVal, new Comparator<Integer>() { // sort in decreasing order
            public int compare(Integer o1, Integer o2) {
                return - o1.compareTo(o2);
            }
        });

        Integer maxVal = listVal.get(0); // get first (max value)

        Collection<Character> returns = new ArrayList<Character>();

        for (Map.Entry<Character, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue().intValue() == maxVal.intValue()) {
                returns.add(entry.getKey());  // get all keys that matches maxVal
            }
        }

        return returns;



    }
    /**
     * Return the most common word or words of a given
     * length in the literary work. There will often
     * only be one such word, but in case of a tie,
     * a collection is returned.
     * @param length the length of words to consider
     * @return the list of most common words
     */
    @Override
    public Collection< String > mostCommon(int length){
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        for (String s : lst) {
            if (hashmap.get(lst.get(0)) == null) {
                hashmap.put(lst.get(0), 1);
            } else {
                hashmap.put(lst.get(0), hashmap.get(s.charAt(0)).intValue() + 1);
            }
        }
        Collection<Integer> values = hashmap.values(); // get values
        List<Integer> listVal = new ArrayList<Integer>(values);
        Collections.sort(listVal, new Comparator<Integer>() { // sort in decreasing order
            public int compare(Integer o1, Integer o2) {
                return - o1.compareTo(o2);
            }
        });

        Integer maxVal = listVal.get(0); // get first (max value)

        Collection<String> returns = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue().intValue() == maxVal.intValue()) {
                returns.add(entry.getKey());  // get all keys that matches maxVal
            }
        }

        return returns;


    }
    /**
     * Return the letters that are most likely to appear at
     * the start of a word from the literary work. Frequency
     * of the words involved is taken into account. There
     * will often only be one letter, but in case of a tie,
     * a collection is returned.
     * @return the most common first letter(s) in the words
     */
    @Override
    public Collection< Character > mostCommonFirstWeighted(){
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        for (String s : lst) {
            if (hashmap.get(s.charAt(0)) == null) {
                hashmap.put(s.charAt(0), 1);
            } else {
                hashmap.put(s.charAt(0), hashmap.get(s.charAt(0)).intValue() + 1);
            }
        }
        Collection<Integer> values = hashmap.values(); // get values
        List<Integer> listVal = new ArrayList<Integer>(values);
        Collections.sort(listVal, new Comparator<Integer>() { // sort in decreasing order
            public int compare(Integer o1, Integer o2) {
                return - o1.compareTo(o2);
            }
        });

        Integer maxVal = listVal.get(0); // get first (max value)

        Collection<Character> returns = new ArrayList<Character>();

        for (Map.Entry<Character, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue().intValue() == maxVal.intValue()) {
                returns.add(entry.getKey());  // get all keys that matches maxVal
            }
        }

        return returns;

    }
}