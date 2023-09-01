package utility;

/**
 * Utility class for string manipulation operations.
 *
 * @author Ho Khee Wei
 */
public abstract class StringUtility {

    /**
     * Removes the first word from a string.
     *
     * @param s The input string
     * @return New string without first word.
     */
    public static String removeFirstWord(String s) {
        int indexOfSpace = s.indexOf(' ');
        if (indexOfSpace == -1) {
            return "";
        }
        return s.substring(indexOfSpace + 1);
    }

    /**
     * Extracts the first word from a string.
     *
     * @param s The input string.
     * @return The first word.
     */
    public static String getFirstWord(String s) {
        int indexOfSpace = s.indexOf(' ');
        if (indexOfSpace == -1) {
            return s;
        }
        return s.substring(0, indexOfSpace);
    }

}
