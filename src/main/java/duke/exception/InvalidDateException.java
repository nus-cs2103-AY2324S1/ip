package duke.exception;

/**
 * A class that represents all exceptions thrown by the program when 
 * dates provided when adding tasks is of the wrong format
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for exception
     */
    public InvalidDateException() {
        super("☹ OOPS!!! Date format given is invalid. Please input date in the following format: yyyy-mm-dd");
    }
}
