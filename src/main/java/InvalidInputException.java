public class InvalidInputException extends DukeException {

    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "I don't understand what you just said :(";
    }

}
