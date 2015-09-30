import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Finds a list of all of the words that are in one text file and not in another
 *
 * @author Ridout and Hannah Kim
 * @version September 2013
 */

public class FileComparison
{

    /**
     * Finds and returns a list of all of the words in the given file in lower
     * case and in alphabetical order. If a word appears more than once in the file,
     * it will appear only once in the List (i.e. The List will have no duplicates)
     *
     * @param fileName
     *            the name of the file
     * @return a List of all of the words in this file 
     * Postcondition: The returned list should contain only lowercase words
     *                and it should be in alphabetical order
     * @throws FileNotFoundException
     *             if the given file doesn't exist
     */
    public static ArrayList < String > wordsInFile (String fileName)
        throws FileNotFoundException
    {
        // Read the file
    	Scanner inFile = new Scanner (new File (fileName));
    	inFile.useDelimiter ("[^\\p{Alpha}]+");
    	
    	// Copy the words into the list
    	ArrayList<String> words = new ArrayList <String> ();
    	while (inFile.hasNext())
    	{
    		String addWord = (inFile.next().toLowerCase());
    		if (!words.contains (addWord))
    			words.add(addWord);
    	}
    	
    	// Close the file
    	inFile.close();
    	
    	// Sort the list
        Collections.sort(words);
        
        // Return the list
    	return words;
    }


    /**
      * Find the words that are in the first List but not in the second List
      *
      * @param first the first List
      * @param second the second List
      * Precondition: Both of the given Lists will be in alphabetical order
      * @return a List of words that are in first but not in second
      * Postcondition: The returned List of words should be in alphabetical order
      */
    public static ArrayList < String > inFirstOnly (ArrayList < String > first,
            ArrayList < String > second)
    {
        // Create an ArrayList to put the words that are in the first List
    	// but not in the second List
    	ArrayList<String> firstWords = new ArrayList<String> ();
    	
    	// Compare words from both files
    	for (int i = 0; i < first.size(); i++)
    	{
    		if (!second.contains(first.get(i)))
    			firstWords.add(first.get(i));
    	}
    	
    	// Return the list
    	return firstWords;
    }


    public static void main (String[] args) throws FileNotFoundException
    {
        // Set up a keyboard for file name input
        Scanner keyboard = new Scanner (System.in);

        // Input the names of the two files
        System.out.println ("Comparing Words in Two Files");
        System.out.print ("Enter the name of the first file: ");
        String firstFileName = keyboard.next ();
        System.out.print ("Enter the name of the second file: ");
        String secondFileName = keyboard.next ();

        // Read in the files and find a list of all of the words in
        // the first file that are not in the second file
        long start = System.nanoTime ();

        // Create word lists for each file using the method above
        ArrayList <String> wordsInFirst = wordsInFile (firstFileName);
        ArrayList <String> wordsInSecond = wordsInFile (secondFileName);

        // Create a list of all of the words that are in first but not second
        ArrayList <String> wordsToFind = inFirstOnly (wordsInFirst, wordsInSecond);

        long stop = System.nanoTime ();

        System.out.printf (
                "There are %d words in \"%s\" that are not in \"%s\"%n",
                wordsToFind.size (), firstFileName, secondFileName);

        int elementsToDisplay = Math.min (wordsToFind.size (), 20);

        System.out.println ("The words are: " +
                wordsToFind.subList (0, elementsToDisplay));

        System.out.printf ("Run time: %.1f ms%n", (stop - start) / 1000000.0);
        System.out.println ("End of Program");
    }
}
