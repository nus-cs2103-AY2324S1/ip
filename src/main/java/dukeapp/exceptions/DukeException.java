package dukeapp.exceptions;

import dukeapp.DukeConstants;

/**
 * Represents Duke related exceptions.
 */
public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(String.format(DukeConstants.ERROR_MESSAGE, message));
    }
}
