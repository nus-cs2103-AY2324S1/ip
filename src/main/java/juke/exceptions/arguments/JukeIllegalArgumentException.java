package juke.exceptions.arguments;

import juke.exceptions.JukeException;

/**
 * Exception that is thrown when illegal argument(s) is/are used anywhere.
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
