package bot.parsers;

public class InputParser {
    private static final String SPLIT_AT_SPACE = " ";
    private static final String SPLIT_AT_BY = " /by ";
    private static final String SPLIT_AT_FROM = " /from ";
    private static final String SPLIT_AT_TO = " /to ";

    /**
     * Returns a String Array of the String split at the first space
     *
     * @param str String of the user input
     * @return string array of the str argument
     */
    public static String[] getSplitAtSpace(String str) {
        return str.split(InputParser.SPLIT_AT_SPACE, 2);
    }

    /**
     * Returns a String Array of the String split at the first /by
     *
     * @param str String of the user input
     * @return string array of the str argument
     */
    public static String[] getSplitAtBy(String str) {
        return str.split(InputParser.SPLIT_AT_BY, 2);
    }

    /**
     * Returns a String Array of the String split at the first /from
     *
     * @param str String of the user input
     * @return string array of the str argument
     */
    public static String[] getSplitAtFrom(String str) {
        return str.split(InputParser.SPLIT_AT_FROM, 2);
    }

    /**
     * Returns a String Array of the String split at the first /to
     *
     * @param str String of the user input
     * @return string array of the str argument
     */
    public static String[] getSplitAtTo(String str) {
        return str.split(InputParser.SPLIT_AT_TO, 2);
    }

    /**
     * Returns a String of the first index of the String Array.
     *
     * @param arr String of the user input
     * @return String of the first array
     */
    public static String getLeftOfSplit(String[] arr) {
        return arr[0];
    }

    /**
     * Returns a String of the second index of the String Array.
     *
     * @param arr String of the user input
     * @return String of the second array
     */
    public static String getRightOfSplit(String[] arr) {
        return arr[1];
    }

}
