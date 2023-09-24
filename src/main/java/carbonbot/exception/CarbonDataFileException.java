package carbonbot.exception;

/**
 * Thrown to indicate an invalid task index was supplied.
 */
public class CarbonDataFileException extends CarbonException {

    /**
     * Constructs a CarbonDataFileException.
     */
    public CarbonDataFileException(String errorMessage) {
        super("The data file is corrupted: " + errorMessage);
    }
}
