package duke;

/**
 * The chat bot.
 */
public class Duke {

    /**
     * Enclosing exception class for all exceptions that arise when running the program.
     */
    public static class DukeRuntimeException extends RuntimeException {
        public DukeRuntimeException(String message) {
            super(message);
        }
    }

    /**
     * All exceptions that arise when parsing user input.
     */
    public static class DukeParserException extends DukeRuntimeException {
        public DukeParserException(String message) {
            super(message);
        }
    }

    /**
     * Exceptions that arise when the user input is not a valid command.
     */
    public static class DukeInvalidCommandException extends DukeParserException {
        public DukeInvalidCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exceptions that arise when the user input is not in the correct format.
     */
    public static class DukeWrongCommandFormatException extends DukeParserException {
        public DukeWrongCommandFormatException(String message) {
            super(message);
        }
    }

    /**
     * Exceptions that arise when the file is corrupted.
     */
    public static class DukeInvalidFileException extends DukeRuntimeException {
        public DukeInvalidFileException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.runCliController();
    }
}
