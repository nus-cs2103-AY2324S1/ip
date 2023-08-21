package main.java.exceptions;

/**
 * Exception that is thrown when an illegal argument(s) are used.
 */
public class JukeIllegalArgumentException extends JukeException {
    /** Expected Arguments. */
    private final String expectedArguments;

    public JukeIllegalArgumentException(String err, String expectedArguments) {
        super(err);
        this.expectedArguments = expectedArguments;
    }

    @Override
    public String toString() {
        return super.toString() + "\nUsage: " + expectedArguments;
    }
}
