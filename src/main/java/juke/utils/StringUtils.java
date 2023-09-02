package juke.utils;

import java.util.StringTokenizer;

/**
 * Utility class that provides methods for manipulating Strings.
 */
public class StringUtils {
    /**
     * Wraps the given text to the specified length. Text is automatically
     * delimited by the carriage return character.
     * <p>
     * Inspiration for this method comes from:
     * <a href="https://stackoverflow.com/questions/41056517/breaking-a-long-url-to-several-line-in-javadoc">...</a>
     *
     * @param text Text to wrap
     * @param maxWrap Max number of characters that can exist on a line
     * @return Text wrapped to the specified maxWrap length
     */
    public static String wrap(String text, int maxWrap) {
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
    }
}
