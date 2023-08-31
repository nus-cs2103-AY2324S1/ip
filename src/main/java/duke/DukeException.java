package duke;

/**
 * Exceptions that are unique to the duke programme.
 */
public class DukeException extends RuntimeException{

    /**
     * DukeException constructor that takes in a String.
     * @param errMes Duke's error message.
     */
    public DukeException(String errMes) {
        super(errMes);
    }
}
