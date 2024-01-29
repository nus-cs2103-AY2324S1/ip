package duke.exception;

/**
 * Signals when storage cannot be properly loaded
 */
public class DukeLoadingException extends Exception {
    /**
     * Constructs an duke.exception.DukeLoadingException with the specified detail message.
     *
     * @param e - description of the loading exception
     */
    public DukeLoadingException(String e) {
        super(e);
    }
}
