/**
 * SidInvalidFormatException exception which is an extension of SidException.
 */
public class SidInvalidFormatException extends SidException {

    /**
     * Constructor for SidInvalidFormatException.
     * 
     * @param errorMessage
     */
    public SidInvalidFormatException(String errorString) {
        super(errorString);
    }
}
