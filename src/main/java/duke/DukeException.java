package duke;

/**
 * Custom exception for the Duke program.
 */
public class DukeException extends Exception {
    /**
     * Custom exception for invalid date and time values.
     */
    public static class DukeDateTimeException extends DukeException {
        /**
         * Creates an exception with the invalid datetime string.
         *
         * @param s The invalid date and time value.
         */
        public DukeDateTimeException(String s) {
            super("Err: DateTime given does not fit the format. Expected: yyyy MM DD HHmm. Got " + s);
        }
    }

    /**
     * Takes the string and creates an exception with the error message as string.
     *
     * @param s The error message.
     */
    public DukeException(String s) {
        super(s);
    }
}
