
public class Parser {
    private static final String SPACE = " ";
    private static final String SPLIT_AT_BY = " /by ";
    private static final String SPLIT_AT_FROM = " /from ";
    private static final String SPLIT_AT_TO = " /to ";

    public static String taskInfo(String str) {
        int idx = str.indexOf(Parser.SPACE);
        return idx > -1 ? str.substring(0, idx) : str;
    }

    public static String remainderInfo(String str) {
        int idx = str.indexOf(Parser.SPACE);
        return idx > -1 ? str.substring(idx + 1) : str;
    }

    public static String[] getSplitAtBy(String str) {
        return str.split(Parser.SPLIT_AT_BY, 2);
    }

    public static String[] getSplitAtFrom(String str) {
        return str.split(Parser.SPLIT_AT_FROM, 2);
    }

    public static String[] getSplitAtTo(String str) {
        return str.split(Parser.SPLIT_AT_TO, 2);
    }

    public static String getLeftOfSplit(String[] arr) {
        return arr[0];
    }

    public static String getRightOfSplit(String[] arr) {
        return arr[1];
    }

}
