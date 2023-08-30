/**
 * SidException exception which is an extension of Exception
 */
public class SidException extends Exception {
    public SidException(String errorMessage) {
        super("Sidtacphi: " + errorMessage);
    }
}
