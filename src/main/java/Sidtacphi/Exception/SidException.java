package sidtacphi.exception;

/**
 * SidException exception which is an extension of Exception.
 */
public class SidException extends Exception {

    /**
     * Constructs a SidException exception.
     *
     * @param errorMessage
     */
    public SidException(String errorMessage) {
        super("Sidtacphi: " + errorMessage);
    }
}
