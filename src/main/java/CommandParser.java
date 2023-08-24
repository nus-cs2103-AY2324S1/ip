import java.util.Map;
import java.util.HashMap;

public abstract class CommandParser {

    /**
     * Parse the input given by user.
     * 
     * @param input User input
     * @return Map of options and values.
     */
    public static Map<String, String> parse(String input) {
        String command = input.split(" ")[0];
        String[] arguments = input.split(" /");
        Map<String, String> res = new HashMap<>();
        for (String arg : arguments) {
            String option = getFirstWord(arg);
            String value = removeFirstWord(arg);

            if (option.equals(command)) {
                res.put("command", command);
                res.put("description", value);
            } else {
                res.put(option, value);
            }

        }
        return res;
    }

    /**
     * Removes the first word from a string.
     * 
     * @param s The input string
     * @return New string without first word.
     */
    private static String removeFirstWord(String s) {
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
    private static String getFirstWord(String s) {
        int indexOfSpace = s.indexOf(' ');
        if (indexOfSpace == -1) {
            return s;
        }
        return s.substring(0, indexOfSpace);
    }
}