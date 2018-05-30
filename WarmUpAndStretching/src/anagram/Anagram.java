package anagram;

/**
 * This utility class can test whether two strings are anagrams.
 *
 * @author Claude Anderson.
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * For example, "Data Structure" and "Saturated Curt" are anagrams, as are
	 * "Elvis" and "Lives".
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true iff s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {
		boolean hasLetter = false;
		String ss1 = "";
		String ss2 = "";
		for (int i = 0; i < s1.length(); i++) {
			if (s1.substring(i, i + 1) != " ") {
				ss1 += s1.substring(i, i + 1);
			}
		}
		for (int i = 0; i < s2.length(); i++) {
			if (s2.substring(i, i + 1) != " ") {
				ss2 += s2.substring(i, i + 1);
			}
		}
		ss1 = ss1.toLowerCase();
		ss2 = ss2.toLowerCase();
		if (ss1.length()!=ss2.length()){
			return false;
		}
		for (int i = 0; i < ss1.length(); i++) {
			hasLetter = false;
			for (int j = 0; j < ss2.length(); j++) {
				if (ss1.substring(i, i + 1).equals(ss2.substring(j, j + 1))) {
					hasLetter = true;
				}
			}
			if (!hasLetter) {
				return false;
			}
		}
		return true;
	}
}
