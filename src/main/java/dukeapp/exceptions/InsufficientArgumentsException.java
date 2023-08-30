package dukeapp.exceptions;

import dukeapp.DukeConstants;

/**
 * Represents exception when there are insufficient arguments when creating a
 * task.
 */
public class InsufficientArgumentsException extends IllegalArgumentException {
    public InsufficientArgumentsException(String message) {
        super(String.format(DukeConstants.ERROR_MESSAGE, message));
    }
}
