package duke;

/**
 * The chat bot.
 */
public class Duke {

    /**
     * All exceptions that arise when parsing user input.
     */
    public static class ParserException extends RuntimeException {
        public ParserException(String message) {
            super(message);
        }
    }

    /**
     * Exceptions that arise when the user input is not a valid command.
     */
    public static class WrongCommandException extends ParserException {
        public WrongCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exceptions that arise when the user input is not in the correct format.
     */
    public static class WrongFormatException extends ParserException {
        public WrongFormatException(String message) {
            super(message);
        }
    }

    /**
     * Exceptions that arise when the file is corrupted.
     */
    public static class InvalidFileException extends RuntimeException {
        public InvalidFileException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }
}
