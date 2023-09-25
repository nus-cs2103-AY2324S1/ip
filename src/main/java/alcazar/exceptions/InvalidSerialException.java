package alcazar.exceptions;

/**
 * Encapsulates the exception when an invalid serial number is passed
 */
public class InvalidSerialException extends AlcazarException {

    /**
     * Constructs a new InvalidSerialException
     * @param message The error message
     */
    public InvalidSerialException(String message) {
        super(message);
    }
}
