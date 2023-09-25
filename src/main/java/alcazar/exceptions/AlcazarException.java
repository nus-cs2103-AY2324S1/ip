package alcazar.exceptions;

/**
 * Encapsulates the custom-exceptions thrown in ths project
 */
public class AlcazarException extends Exception {

    /**
     * Constructs an Alcazar Exception
     * @param message The error message
     */
    public AlcazarException(String message) {
        super(message);
    }
}
