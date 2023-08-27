package juke.exceptions.arguments;

import juke.exceptions.JukeException;

/**
 * Exception that is thrown when illegal argument(s) is/are used anywhere.
 */
public class JukeIllegalArgumentException extends JukeException {
    /**
     * Constructor for {@code JukeIllegalArgumentException}.
     * @param err Error Description
     */
    public JukeIllegalArgumentException(String err) {
        super(err);
    }

    /**
     * String representation of {@code JukeIllegalArgumentException}.
     * @return String representation of the exception
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
