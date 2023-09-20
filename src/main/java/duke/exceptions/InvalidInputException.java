package duke.exceptions;

/** Encapsulates exceptions due to invalid input. */
public class InvalidInputException extends DukeException {

    /**
     * Constructor for Exceptions.InvalidInputException.
     * @param message Exception message.
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Returns the description of the exception.
     * @return Description of exception.
     */
    @Override
    public String getMessage() {
        return "I don't understand what you just said :(";
    }

}
