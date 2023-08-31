package linus.util;

public class Parser {

    /**
     * Parses the input string into a command and data.
     * @param input The input string.
     * @return String[]
     */
    public static String[] parse(String input) {
        String[] inputSplit = input.split(" ", 2);
        String command = inputSplit[0];
        String data = inputSplit.length == 2 ? inputSplit[1] : "";
        return new String[]{command, data};
    }
}
