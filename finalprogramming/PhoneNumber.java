import java.util.*;
import java.lang.*;

import javax.sql.rowset.FilteredRowSet;

/** Program that generates all possible mnemonics for a given phone number.
 * 
 * @author Preston Ito
 */
public class PhoneNumber {

	// Mapping from digits on a phone keypad to the corresponding letters of the
	// alphabet.
	private static final String[] LETTERS = {"", "", "ABC", "DEF", "GHI", "JKL",
			"MNO", "PQRS", "TUV", "WXYZ"};


	/** Returns the most significant digit in a given non-negative integer.
	 * 
	 * Examples:
	 *     getMostSignificantDigit(561) --> 5
	 *     getMostSignificantDigit(20)  --> 2
	 *     getMostSignificantDigit(3)   --> 3
	 * 
	 * @param number the number whose most significant digit we wish to find.
	 * @throws IllegalArgumentException if number < 0.
	 * @return the most significant digit in number.
	 */
	private static int getMostSignificantDigit(int number) {
		if (number < 0)
			throw new IllegalArgumentException("number must be > 0.");

		return Integer.parseInt(Integer.toString(number).substring(0, 1));
	}


	/** Returns all but the most significant digit from a given non-negative
	 * integer.
	 * 
	 * Examples:
	 *     getLeastSignificantDigits(561) --> 61
	 *     getLeastSignificantDigits(20)  -->  0
	 *     getLeastSignificantDigits(3)   -->  0
	 * 
	 * @param number number whose least significant digits we wish to extract.
	 * @throws IllegalArgumentException if number < 0.
	 * @return all but the most signficant digit in number. If number has only
	 * one significant digit, then 0 is returned.
	 */
	private static int getLeastSignificantDigits(int number) {
		if (number < 0)
			throw new IllegalArgumentException("number must be > 0.");

		try {
			return Integer.parseInt(Integer.toString(number).substring(1));
		}
		catch (NumberFormatException e) {            
			return 0;
		}
	}


	/** Returns the set of all mnemonics that can be formed with the given
	 * integer.
	 * 
	 * @param number the number whose corresponding mnemonics we wish to find.
	 * @return the set of mnemonics that can be formed for the given number
	 * on a standard phone keypad.
	 */
	public static Set<String> listMnemonics(int number) {    

		TreeSet<String> stringSet = new TreeSet<>();
		
		int firstNum = findGoodFirstNumber(number);
		if (firstNum != getMostSignificantDigit(number)) {
			number = getRidOfBadNumbers(number);
		}
		
		String phoneLetters = LETTERS[firstNum];
		char[] phoneChar = phoneLetters.toCharArray();
		
		//iterate through each letter of the string associated with the first number and call recursive helper
		for (int i = 0; i < phoneChar.length; i ++) {
			Character letter = phoneChar[i];
			String stringLetter = letter.toString();
			listMnemonicsHelper(stringLetter, number, stringSet);
		}

		return stringSet; 
	}

	/** Recursive helper that finds first non-1 digit
	 * 
	 * @param firstNum
	 * @return
	 */
	private static int findGoodFirstNumber(int firstNum) {
		if (getMostSignificantDigit(firstNum) != 1) {
			return getMostSignificantDigit(firstNum);
		}
		return findGoodFirstNumber(getLeastSignificantDigits(firstNum));
	}

	/** Recursive helper that gets rid of all 1's in the number 
	 * 
	 * @param number
	 * @return
	 */
	private static int getRidOfBadNumbers(int number) {
		if (getMostSignificantDigit(number) == 1) {
			return getRidOfBadNumbers(getLeastSignificantDigits(number));
		}
		return number;
	}

	/** Recursive helper that goes through each possible combination 
	 * 
	 * @param mnemonic
	 * @param number
	 * @param strings
	 * @return
	 */
	private static boolean listMnemonicsHelper(String mnemonic, int number, TreeSet<String> strings) {

		//adds the string to the set 
		if (getLeastSignificantDigits(number) == 0) {
			strings.add(mnemonic);
			return true;
		}

		int nextNum = getMostSignificantDigit(getLeastSignificantDigits(number));

		String phoneLetters = LETTERS[nextNum];
		char[] phoneChar = phoneLetters.toCharArray();
		for (int i = 0; i < phoneChar.length; i ++) {
			Character letter = phoneChar[i];
			String stringLetter = letter.toString();
			String newMnem = mnemonic + stringLetter;
			if (listMnemonicsHelper(newMnem, getLeastSignificantDigits(number), strings)) {
			}
		}
		return false;
	}

	/** Tester method. */
	public static void main(String[] args) {

		String[] m = {"MAD", "MBD", "MCD", "NAD", "NBD", "NCD", "OAD", "OBD",
				"OCD", "MAE", "MBE", "MCE", "NAE", "NBE", "NCE", "OAE", "OBE",
				"OCE", "MAF", "MBF", "MCF", "NAF", "NBF", "NCF", "OAF", "OBF",
		"OCF"};
		Set<String> mSet = new TreeSet<String>();
		for (String s : m)
			mSet.add(s);

		//Should print same elements as listed above
		System.out.println(listMnemonics(623));

		// // Should print true (quick way to check your answer)
		System.out.println(mSet.equals(listMnemonics(623)));

		// // Should print [P, Q, R, S] (order not important)
		System.out.println(listMnemonics(70));

		//Preston's test (these should all be the same)
		System.out.println(listMnemonics(16023));
		System.out.println(listMnemonics(11116023));
		System.out.println(listMnemonics(1010160230));


	}

}