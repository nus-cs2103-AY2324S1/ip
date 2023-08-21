package main.java;

import java.util.Optional;

/**
 * This class represents an exception thrown by Juke.
 */
public class DukeException extends Exception {
    /** Error description. */
    private final String err;

    /**
     * Constructor to create an Error Action.
     * @param err Error description
     */
    public DukeException(String err) {
        this.err = err;
    }

    /**
     * String representation of the Exception.
     * @return String representing the exception this object is encapsulating
     */
    @Override
    public String toString() {
        return this.err;
    }
}
