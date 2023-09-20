package juke.commons.exceptions.arguments;

import juke.commons.exceptions.JukeException;

/**
 * Represents illegal argument(s) being used anywhere in Juke. This exception is the
 * superclass of all other exceptions that involve the use of illegal arguments/values.
 */
public class JukeIllegalArgumentException extends JukeException {
    /**
     * Creates an instance of {@code JukeIllegalArgumentException}.
     *
     * @param error Error Description
     */
    public JukeIllegalArgumentException(String error) {
        super(error);
    }

    /**
     * Returns String representation of {@code JukeIllegalArgumentException}.
     *
     * @return String representation of the exception
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
