package main.java.juke.exceptions.arguments;

import main.java.juke.exceptions.JukeException;

/**
 * Exception that is thrown when an illegal argument(s) are used.
 * <p>
 * This exception is unchecked as we cannot anticipate the value of
 * the input arguments given by the user.
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
