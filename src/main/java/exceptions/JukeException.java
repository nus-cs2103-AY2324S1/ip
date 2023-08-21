package main.java.exceptions;

/**
 * This class represents a generic exception thrown by Juke.
 */
public class JukeException extends Exception {
    /** Error description. */
    private final String err;

    /**
     * Constructor to create a JukeException.
     * @param err Error description
     */
    public JukeException(String err) {
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
