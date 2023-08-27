package juke.exceptions.storage;

/**
 * Represents an error with the reading data from the datafile.
 */
public class JukeStorageReadException extends JukeStorageException {

    /**
     * Constructor to create a {@code JukeStorageReadException}.
     *
     * @param error Error description
     */
    public JukeStorageReadException(String error) {
        super(error);
    }
}
