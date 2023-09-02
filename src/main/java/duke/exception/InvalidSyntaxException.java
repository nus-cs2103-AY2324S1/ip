package duke.exception;

/** This class handles exceptions for invalid syntax for commands from Duke bot.*/

public class InvalidSyntaxException extends DukeException {

    /**
     * The constructor for InvalidSyntaxException
     * @param errorMsg The error message for the InvalidSyntaxException
     */
    public InvalidSyntaxException(String errorMsg) {
        super(errorMsg);
    }
}
