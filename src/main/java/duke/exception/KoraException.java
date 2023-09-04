package duke.exception;

/**
 * Custom exception specific to Kora
 */
public class KoraException extends Exception {

    /**
     * Class constructor of KoraException.
     * @param message String that contains reason for the exception.
     */
    public KoraException(String message) {
        super(message);
    }

}
