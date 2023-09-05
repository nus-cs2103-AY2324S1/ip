package chad.chatengine;

/**
 * Represents exceptions specific to the Chad chatbot application.
 */
public class ChadException extends Exception {

    /**
     * Constructs a new ChadException with a specific message.
     * @param message the detail message for the exception.
     */
    public ChadException(String message) {
        super(message);
    }

    /**
     * Exception thrown when an invalid command is encountered.
     */
    public static class InvalidCommandException extends ChadException {

        /**
         * Constructs a new InvalidCommandException with a specific message.
         * @param message the detail message for the exception.
         */
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an invalid argument is encountered.
     */
    public static class InvalidArgumentException extends ChadException {

        /**
         * Constructs a new InvalidArgumentException with a specific message.
         * @param message the detail message for the exception.
         */
        public InvalidArgumentException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an invalid format is encountered.
     */
    public static class InvalidFormatException extends ChadException {

        /**
         * Constructs a new InvalidFormatException with a specific message.
         * @param message the detail message for the exception.
         */
        public InvalidFormatException(String message) {
            super(message);
        }
    }
}
