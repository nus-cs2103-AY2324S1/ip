package juke.exceptions.arguments;

/**
 * Exception that is thrown when an illegal command argument is entered into
 * the command line.
 */
public class JukeIllegalCommandArgumentException extends JukeIllegalArgumentException {
    /** String representing the expected arguments for a command. */
    private final String expectedArguments;

    /**
     * Constructor for {@code JukeIllegalCommandArgumentException}
     *
     * @param err Error Description
     * @param expectedArguments Expected arguments
     */
    public JukeIllegalCommandArgumentException(String err, String expectedArguments) {
        super(err);
        this.expectedArguments = expectedArguments;
    }

    /**
     * String representation of {@code JukeIllegalCommandArgumentException}.
     *
     * @return String representation of the exception
     */
    @Override
    public String toString() {
        return super.toString() + "\nUsage: " + expectedArguments;
    }
}
