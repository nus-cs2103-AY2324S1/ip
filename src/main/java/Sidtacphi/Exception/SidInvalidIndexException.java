package sidtacphi.exception;

/**
 * SidInvalidIndexException exception which is an extension of SidException.
 */
public class SidInvalidIndexException extends SidException {

    /**
     * Constructs a SidInvalidFormatException exception.
     *
     * @param errorMessage
     */
    public SidInvalidIndexException(String errorString) {
        super(errorString);
    }
}
