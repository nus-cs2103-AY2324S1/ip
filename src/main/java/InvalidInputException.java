public class InvalidInputException extends DukeException {
    private static final String ERROR_MESSAGE = "I don't understand!";
    public InvalidInputException() {
        super(ERROR_MESSAGE);
    }
}
