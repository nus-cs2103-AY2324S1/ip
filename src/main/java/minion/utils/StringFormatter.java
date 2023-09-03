package minion.utils;

/**
 * Represents a string formatter.
 */
public class StringFormatter {
    /**
     * Formats strings.
     * @param args the strings to be formatted.
     * @return the formatted strings.
     */
    public static String format(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg: args) {
            sb.append(arg).append("\n");
        }
        return sb.toString();
    }
}
