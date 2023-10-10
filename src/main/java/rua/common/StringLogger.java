package rua.common;

/**
 * Logs String from different classes for future use
 */
public class StringLogger {
    private static String log = "";

    /**
     * Appends a string to the string logger.
     *
     * @param str The string to be appended to the logger.
     */
    public static void append(String str) {
        log = log + str;
    }

    /**
     * Clears the string logger.
     */
    public static void clear() {
        log = "";
    }

    /**
     * Gets the stored string in the string logger.
     *
     * @return The string in the logger.
     */
    public static String getLog() {
        return log;
    }
}
