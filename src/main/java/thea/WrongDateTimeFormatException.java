package thea;

/**
 * Represents an exception to use when users input date and time in wrong formats.
 */
public class WrongDateTimeFormatException extends Exception {

    /**
     * Constructs a new WrongDateTimeFormatException object.
     *
     * @param errorMessage errorMessage of the exception.
     */
    public WrongDateTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
