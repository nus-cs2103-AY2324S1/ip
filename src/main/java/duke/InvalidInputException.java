package duke;

/** Exception which occurs when the user input is invalid. */
public class InvalidInputException extends DukeException {

    /**
     * Class constructor containing the message of the exception
     * @param message the string message associated with the exception
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
