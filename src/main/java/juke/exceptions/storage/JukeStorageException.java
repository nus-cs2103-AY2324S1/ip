package juke.exceptions.storage;

import juke.exceptions.JukeException;

/**
 * Represents a generic error with the storing, retrieving or modifying of data
 * in the datafile.
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
