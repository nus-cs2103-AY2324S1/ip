package exception;

/**
 * Exception for the event where user input has missing arguments.
 */
public class MissingArgumentException extends Exception {

    private String missingArgument;

    /**
     * Constructor for MissingArgumentException
     * @param missingArgument a string depicting arguments that are missing from user input
     */
    public MissingArgumentException(String missingArgument) {
        this.missingArgument = missingArgument;
    }

    /**
     * Returns a string to inform users of the exception
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "the following information is missing: " + missingArgument;
    }
}
