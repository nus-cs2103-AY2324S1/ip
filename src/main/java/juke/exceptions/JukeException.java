package juke.exceptions;

/**
 * Base Exception class for which all other Juke Exceptions are based on.
 * <p>
 * Note that as this program is expected to parse any and all errors into a readable
 * format before being thrown back to the user through {@code JukeExceptionAction},
 * almost all exceptions should be unchecked and be caught only by the main event
 * loop which executes the Actions.
 */
public class JukeException extends RuntimeException {
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
