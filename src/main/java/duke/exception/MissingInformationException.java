package duke.exception;

/**
 * A class that represents all exceptions thrown by the program when information is expected
 * in accordance to user commands, but not provided
 */
public abstract class MissingInformationException extends DukeException {
    /**
     * Constructor for exception
     */
    public MissingInformationException(String message) {
        super(message);
    }

}
