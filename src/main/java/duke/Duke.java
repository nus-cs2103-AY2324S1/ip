package duke;

/**
 * The chat bot.
 */
public class Duke {

    /**
     * All exceptions that arise when parsing user input.
     */
    static class ParserException extends RuntimeException {
        public ParserException(String message) {
            super(message);
        }
    }

    static class WrongCommandException extends ParserException {
        public WrongCommandException(String message) {
            super(message);
        }
    }

    static class WrongFormatException extends ParserException {
        public WrongFormatException(String message) {
            super(message);
        }
    }

    static class InvalidFileException extends RuntimeException {
        public InvalidFileException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }
}
