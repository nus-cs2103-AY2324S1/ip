package parsers;

public class InputParser {
    private static final String SPLIT_AT_SPACE = " ";
    private static final String SPLIT_AT_BY = " /by ";
    private static final String SPLIT_AT_FROM = " /from ";
    private static final String SPLIT_AT_TO = " /to ";

    public static String[] getSplitAtSpace(String str) {
        return str.split(InputParser.SPLIT_AT_SPACE, 2);
    }

    public static String[] getSplitAtBy(String str) {
        return str.split(InputParser.SPLIT_AT_BY, 2);
    }

    public static String[] getSplitAtFrom(String str) {
        return str.split(InputParser.SPLIT_AT_FROM, 2);
    }

    public static String[] getSplitAtTo(String str) {
        return str.split(InputParser.SPLIT_AT_TO, 2);
    }

    public static String getLeftOfSplit(String[] arr) {
        return arr[0];
    }

    public static String getRightOfSplit(String[] arr) {
        return arr[1];
    }

}
