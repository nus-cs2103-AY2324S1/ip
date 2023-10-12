package duke.exceptions;

/**
 * Represents an exception that is thrown when the storage file has an unrecognized format.
 */
public class UnrecognisedFormatException extends Exception {

    /**
     * Constructs an UnrecognisedFormatException with a default error message.
     */
    public UnrecognisedFormatException() {
        super("\u2639 Sorry!!! This data file is corrupted and we do not recognise the format of the file.");
    }

}
