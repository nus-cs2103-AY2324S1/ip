package juke.exceptions.arguments;

/**
 * Exception that is thrown when an illegal command argument is entered into
 * the command line.
 */
public class JukeIllegalCommandArgumentException extends JukeIllegalArgumentException {
    /** Expected Arguments. */
    private final String expectedArguments;

    /**
     * Constructor for {@code JukeIllegalCommandArgumentException}
     * @param error Error Description
     * @param expectedArguments Expected arguments
     */
    public JukeIllegalCommandArgumentException(String error, String expectedArguments) {
        super(error);
        this.expectedArguments = expectedArguments;
    }

    /**
     * String representation of {@code JukeIllegalCommandArgumentException}.
     * @return String representation of the exception
     */
    @Override
    public String toString() {
        return super.toString() + "\nUsage: " + expectedArguments;
    }
}
