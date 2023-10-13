package fluke.exceptions;

/**
 * This exception should be thrown when there is an error parsing the save file.
 */
public class SaveFileParsingException extends FlukeException {
    private static final String ERROR_MESSAGE = "I couldn't understand the save file...";

    /**
     * Constructs a SaveFileParsingException.
     */
    public SaveFileParsingException() {
        super(ERROR_MESSAGE);
    }
}
