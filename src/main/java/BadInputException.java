/**
 * Signals when a user input is unable to be parsed or out of bound
 */
public class BadInputException extends Exception {
    /**
     * Constructs an BadInputException with the specified detail message.
     *
     * @param e - description of the bad input
     */
    public BadInputException(String e) {
        super(e);
    }
}
