package juke.commons.utils;

import java.util.StringTokenizer;

/**
 * Utility class that provides methods for manipulating Strings.
 */
public abstract class StringUtils {
    /**
     * Wraps the given text to the specified length. Text is automatically
     * delimited by the carriage return character.
     * <p>
     * Method is reused from https://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-
     * maximum-length-in-java with minor modifications.
     *
     * @param text Text to wrap
     * @param maxWrap Max number of characters that can exist on a line
     * @return Text wrapped to the specified maxWrap length
     */
    public static String wrap(String text, int maxWrap) {
        //@@author asdfghjkxd-reused
        // Reused from https://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-
        // maximum-length-in-java with minor modifications to the code
        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        StringBuilder lineBuilder = new StringBuilder(text.length());
        int lineCounter = 0;

        while (tokenizer.hasMoreTokens()) {
            String nextWord = tokenizer.nextToken();

            if (nextWord.length() + lineCounter > maxWrap) {
                lineBuilder.append("\n");
                lineCounter = 0;
            }

            lineBuilder.append(nextWord).append(" ");
            lineCounter += nextWord.length();
        }

        return lineBuilder.toString();
        //@@author
    }
}
