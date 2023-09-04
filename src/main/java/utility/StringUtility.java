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

    /**
     * Connects lines into a paragraph separated by line separator.
     *
     * @param lines The input strings.
     * @return The first word.
     */
    public static String joinLines(String... lines) {
        String res = "";
        for (String line : lines) {
            res += line + System.lineSeparator();
        }
        return res;
    }

    /**
     * Connects lines into a paragraph separated by line separator.
     *
     * @param lines The input strings.
     * @return The first word.
     */
    public static String joinLinesArray(String[] lines) {
        String res = "";
        for (String line : lines) {
            res += line + System.lineSeparator();
        }
        return res;
    }
}
