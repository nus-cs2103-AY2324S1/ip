package cheems.exceptions;

public class InputOutputException extends RuntimeException {
    /**
     * Creates a new runtime exception due to IO issues.
     * @param msg The msg to be printed.
     */
    public InputOutputException(String msg) {
        super(msg);
    }
}
