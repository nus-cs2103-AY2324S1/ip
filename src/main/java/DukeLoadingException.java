/**
 * Signals when storage cannot be properly loaded
 */
public class DukeLoadingException extends Exception {
    /**
     * Constructs an DukeLoadingException with the specified detail message.
     *
     * @param e - description of the bad input
     */
    public DukeLoadingException(String e) {
        super(e);
    }
}
