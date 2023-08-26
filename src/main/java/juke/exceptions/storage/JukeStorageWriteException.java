package main.java.juke.exceptions.storage;

/**
 * Represents an error with the writing of data to the datafile.
 */
public class JukeStorageWriteException extends JukeStorageException{

    /**
     * Constructor to create a {@code JukeStorageWriteException}.
     *
     * @param err Error description
     */
    public JukeStorageWriteException(String err) {
        super(err);
    }
}
