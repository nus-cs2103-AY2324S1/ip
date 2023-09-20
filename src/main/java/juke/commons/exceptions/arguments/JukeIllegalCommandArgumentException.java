package juke.commons.exceptions.arguments;

/**
 * Represents an illegal command argument being entered into the command line.
 */
public class JukeIllegalCommandArgumentException extends JukeIllegalArgumentException {
    /** String representing the expected arguments for a command. */
    private final String expectedArguments;

    /**
     * Creates an instance of {@code JukeIllegalCommandArgumentException}
     *
     * @param error Error Description
     * @param expectedArguments Expected arguments
     */
    public JukeIllegalCommandArgumentException(String error, String expectedArguments) {
        super(error);
        this.expectedArguments = expectedArguments;
    }

    /**
     * Returns String representation of {@code JukeIllegalCommandArgumentException}.
     *
     * @return String representation of the exception
     */
    @Override
    public String toString() {
        return super.toString() + "\nUsage: " + expectedArguments;
    }
}
