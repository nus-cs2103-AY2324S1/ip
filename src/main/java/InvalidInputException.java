public class InvalidInputException extends DukeException {
    private static final String ERROR_MESSAGE = "I'm afraid I don't know what that means!";
    public InvalidInputException() {
        super(ERROR_MESSAGE);
    }
}
