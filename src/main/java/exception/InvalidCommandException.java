package exception;

/**
 * Checked Exception for the event where the input command is not a valid command.
 */
public class InvalidCommandException extends Exception {
    /**
     * Returns a string to inform users that the command is invalid
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "This is not a registered command that I know of";
    }
}
