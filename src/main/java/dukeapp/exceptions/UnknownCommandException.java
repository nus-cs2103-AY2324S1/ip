package dukeapp.exceptions;

import dukeapp.DukeConstants;

/**
 * Represents an exception when an unknown command is passed in.
 */
public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException(String message) {
        super(String.format(DukeConstants.ERROR_MESSAGE, message));
    }
}
