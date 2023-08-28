/**
 * Exception class that indicates no number was inputted
 */
public class MissingTaskNumberException extends Exception {
    public MissingTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
