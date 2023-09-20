package juke.commons.exceptions.storage;

import juke.commons.exceptions.JukeException;

/**
 * Represents a generic error with the storing, retrieving or modifying of data
 * in the datafile. This exception is the superclass of any other exception that
 * involves the use of the device's storage.
 */
public class JukeStorageException extends JukeException {
    /**
     * Creates an instance of {@code JukeStorageException}.
     *
     * @param error Error description
     */
    public JukeStorageException(String error) {
        super(error);
    }
}
