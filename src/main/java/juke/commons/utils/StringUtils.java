package juke.commons.utils;

/**
 * Utility class that provides methods for manipulating Strings.
 */
public abstract class StringUtils {
    /**
     * Wraps the given text to the specified length. Text is automatically
     * delimited by the carriage return character.
     *
     * <p>Method is reused from https://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-
     * maximum-length-in-java with minor modifications.</p>
     *
     * @param text Text to wrap
     * @param maxWrap Max number of characters that can exist on a line
     * @return Text wrapped to the specified maxWrap length
     */
    public static String wrap(String text, int maxWrap) {
        // allow processing for null inputs
        if (text == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        int charCounter = 0;

        // loop through the characters of the text
        for (char c : text.toCharArray()) {
            // reset the count if the character input is a new line
            if (c == '\n') {
                charCounter = 0;
            }

            // if the length of the current line is > maxWrap,
            // add the delimiter and reset the charCounter
            if (charCounter == maxWrap) {
                builder.append("\n");
                charCounter = 0;
            }

            // add the character to the string builder and increment charCounter
            builder.append(c);
            charCounter++;
        }

        return builder.toString();
    }
}
