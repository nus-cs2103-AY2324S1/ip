package shiba.parsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses space-separated values.
 */
public class SpaceSeparatedValuesParser {
    /**
     * Converts the given parameters into an escaped space-separated string.
     *
     * @param params Vararg array of parameters to be converted.
     * @return A space-separated string of the given parameters.
     */
    public static String convert(String... params) {
        StringBuilder sb = new StringBuilder();
        for (String param : params) {
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            sb.append(param.replace(" ", "\\ "));
        }

        return sb.toString();
    }

    /**
     * Parses the given space-separated string into a list of strings.
     *
     * @param line The space-separated string to be parsed.
     * @return A list of strings parsed from the given space-separated string.
     */
    public static List<String> parse(String line) {
        ArrayList<String> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < line.length()) {
            char c = line.charAt(index);
            if (c == '\\' && index < line.length() - 1 && line.charAt(index + 1) == ' ') {
                sb.append(' ');
                index += 2;
            } else if (c == ' ') {
                params.add(sb.toString());
                sb.setLength(0);
                index++;
            } else {
                sb.append(c);
                index++;
            }
        }
        if (!sb.isEmpty()) {
            params.add(sb.toString());
        }

        return params;
    }
}
