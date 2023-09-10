package carbonbot.exception;

/**
 * Thrown to indicate an invalid task index was supplied.
 */
public class CarbonDataFileException extends CarbonException {

    /**
     * Constructs a CarbonDataFileException.
     */
    public CarbonDataFileException() {
        super("The data file is corrupted and could not be read. "
                + "A new data file will be created.");
    }
}
