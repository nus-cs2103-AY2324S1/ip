package extensions.exceptions;

/**
 * Thrown when there is an error reading/writing from the file.
 */

// Suppress Checkstyle as DukeIOException contains 3 consecutive upper case letters.
//CHECKSTYLE.OFF: AbbreviationAsWordInName
public class DukeIOException extends DukeException {
    //CHECKSTYLE.ON: AbbreviationAsWordInName

    /**
     * Thrown when there is an error reading/writing from the file.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeIOException(String message) {
        super(message);
    }
}
