import java.util.*;
import java.lang.*;


/**
 * Implementation of a HangmanManager class for HangmanMain
 * 
 * Time Spent: 8hrs 
 * 
 * @author Preston Ito
 * @author Peter Clark
 */
 */
public class HangmanManager {

    private int max;
    private Set<String> words;
    private SortedSet<Character> guessSet;
    private ArrayList<Character> masterPattern;

    /**
     * Constructs a new HangmanManager object
     * 
     * This constructor creates new values 
     * 
     * @param dictionary 
     * @param length
     * @param max
     * @throws IllegalArgumentException
     */
    public HangmanManager(List<String> dictionary, int length, int max) throws IllegalArgumentException {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException();
        }

        guessSet = new TreeSet<>();
        masterPattern = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            masterPattern.add('-');
        }
        this.max = max;

        // should i name this Set "words" so we can use it for the words method?
        words = new HashSet<String>();
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).length() == length) {
                words.add(dictionary.get(i));
            }
        }
    }

    /**
     * returns the current set of words
     * 
     * @return the current set of words
     */
    public Set<String> words() {
        return words;
    }

    /**
     * returns the number of guesses that the player has left
     * 
     * @return the number of guesses left
     */
    public int guessesLeft() {
        return max - guessSet.size();
    }

    /**
     * 
     * @return the set of characters guessed by the player
     * @throws IllegalStateException
     */
    public SortedSet<Character> guesses() throws IllegalStateException {
        // need access to the guesses made
        // they need to be in a set
        if (words().isEmpty()) {
            throw new IllegalStateException();
        }
        return guessSet;
    }

    /**
     * Returns the current pattern to be displayed for the hangman game
     * 
     * @return
     * @throws IllegalStateException
     */
    public String pattern() throws IllegalStateException {
        if (words().isEmpty()) {
            throw new IllegalArgumentException();
        }

        // only returns the current pattern, have an atribute that stores pattern
        String pattern = "";
        for (Character ch : masterPattern) {
            pattern = pattern + ch + " ";
        }
        pattern = pattern.trim();

        return pattern;
    }

    /**
     * 
     * @param guess
     * @return
     */
    private Map<String, ArrayList<String>> patternCollector(char guess) {
        // map: (keys -> tempPattern string) & (values -> ArrayList of words from dict w
        // that pattern)
        Map<String, ArrayList<String>> allPatterns = new HashMap<>();

        // System.out.println("1: " + allPatterns.keySet());

        for (String word : words) {
            String tempPattern = "";
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    tempPattern = tempPattern + guess;
                } else {
                    tempPattern = tempPattern + "-";
                }
            }
            if (!allPatterns.containsKey(tempPattern)) {
                allPatterns.put(tempPattern, new ArrayList<String>());
            }
            allPatterns.get(tempPattern).add(word);
        }
        return allPatterns;
    }

    /**
     * 
     * @param guess
     * @return
     */
    public String patternFinder(char guess) {
        int biggest = 0;
        String bestKey = "";
        for (String key : patternCollector(guess).keySet()) {
            if (patternCollector(guess).get(key).size() > biggest) {
                bestKey = key;
                biggest = patternCollector(guess).get(key).size();
            }
        }
        return bestKey;
    }

    /**
     * 
     * @param guess
     * @return
     */
    public Set<String> wordsUpdater(char guess) {
        String bestKey = patternFinder(guess);
        Set<String> tempWords = new HashSet<String>();
        for (String word : patternCollector(guess).get(bestKey)) {
            tempWords.add(word);
        }
        return tempWords;
    }


    /**
     * 
     * @param bestKey
     * @return
     */ 
    public int characterAdder (String bestKey){
        char[] bestKeyAr = bestKey.toCharArray();
        int correctCounter = 0;
        for (int i = 0; i < bestKeyAr.length; i ++) {
            if (bestKeyAr[i] != '-') {
                masterPattern.set(i, bestKeyAr[i]);
                correctCounter++;
            }
        }


        
        return correctCounter;
    }

    private void count ( char[] bestKeyAr) { 
        for (int i =0; i<bestKeyAr.length; i++){
            if (bestKeyAr[i] != '-') {
                max++;

            }
     

    
    /**
     * 
     * @param guess
     * @return
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     */
        ic int record(char guess) throws IllegalStateException, IllegalArgumentException { 
        if (guessesLeft() < 1 || words().isEmpty()) {
            throw new IllegalStateException();
        }
        if (!words().isEmpty() && guessSet.contains(guess)) {
         
        }  
        //records guess by user
        guessSet.add(guess);

        St ring bestKey = patternFinder(guess);
        //adds all the words from the bestkey to words set
        wo rds = wordsUpdater(guess);
        //adds each character from bestKey string to pattern arraylist
        int correctCounter = characterAdder(bestKey);
     



