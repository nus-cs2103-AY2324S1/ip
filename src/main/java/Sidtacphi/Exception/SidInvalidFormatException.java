package sidtacphi.exception;

/**
 * SidInvalidFormatException exception which is an extension of SidException.
 */
public class SidInvalidFormatException extends SidException {

    /**
     * Constructs a SidInvalidFormatException exception.
     *
     * @param errorMessage
     */
    public SidInvalidFormatException(String errorString) {
        super(errorString);
    }
}
