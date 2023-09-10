/**
 * SidException exception which is an extension of Exception.
 */
public class SidException extends Exception {

    /**
     * Constructor for SidException.
     * 
     * @param errorMessage
     */
    public SidException(String errorMessage) {
        super("Sidtacphi: " + errorMessage);
    }
}
