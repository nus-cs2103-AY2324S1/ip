package main.java.exceptions.storage;

import main.java.exceptions.JukeException;

/**
 * Represents an error with the storing, retrieving or modifying of data
 * in the datafile.
 */
public class JukeStorageException extends JukeException {
    /**
     * Constructor to create a JukeStorageException.
     *
     * @param err Error description
     */
    public JukeStorageException(String err) {
        super(err);
    }
}
