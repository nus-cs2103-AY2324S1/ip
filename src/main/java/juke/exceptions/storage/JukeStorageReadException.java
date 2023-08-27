package juke.exceptions.storage;

/**
 * Represents an error with the reading data from the datafile.
 */
public class JukeStorageReadException extends JukeStorageException {
    /**
     * Constructor to create a {@code JukeStorageReadException}.
     *
     * @param err Error description
     */
    public JukeStorageReadException(String err) {
        super(err);
    }
}
