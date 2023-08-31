package sana;

/**
 * Represents a custom exception specific to the Sana application.
 */
public class SanaException extends Exception {

    /**
     * Constructs a new SanaException instance with the specified error message.
     *
     * @param errorMessage the error message associated with this exception
     */
    public SanaException(String errorMessage) {
        super(errorMessage);
    }
}
