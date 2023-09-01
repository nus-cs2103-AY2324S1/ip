public class InvalidInputException extends FlukeException {
    private static final String ERROR_MESSAGE = "I don't understand!";
    public InvalidInputException() {
        super(ERROR_MESSAGE);
    }
}
