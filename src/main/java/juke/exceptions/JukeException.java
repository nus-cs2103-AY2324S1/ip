package juke.exceptions;

/**
 * Base Exception class for which all other Juke Exceptions are based on.
 * <p>
 * Note that as this program is expected to parse any and all errors into a readable
 * format before being thrown back to the user through {@code JukeExceptionAction},
 * almost all exceptions should be unchecked and be caught only by the main event
 * loop which executes the Actions.
 *
 * @see java.lang.RuntimeException
 */
public class JukeException extends RuntimeException {
    /** Error description. */
    private final String error;

    /**
     * Creates an instance of {@code JukeException}.
     *
     * @param error Error description
     */
    public JukeException(String error) {
        this.error = error;
    }

    /**
     * Returns String representation of the {@code JukeException}.
     *
     * @return String representing the exception this object is encapsulating
     */
    @Override
    public String toString() {
        return this.error;
    }
}
