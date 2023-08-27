package juke.exceptions.storage;

import juke.exceptions.JukeException;

/**
 * Represents an error with the storing, retrieving or modifying of data
 * in the datafile.
 */
public class JukeStorageException extends JukeException {
    /**
     * Constructor to create a JukeStorageException.
     *
     * @param error Error description
     */
    public JukeStorageException(String error) {
        super(error);
    }
}
