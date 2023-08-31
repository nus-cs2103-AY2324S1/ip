package duke.exceptions;

/**
 * Represents an exception that is thrown when the provided timeframe is impossible
 * as the deadline is earlier than the start for an event task.
 */
public class BackwardsTimeException extends Exception {

    /**
     * Constructs a BackwardsTimeException with a default error message.
     */
    public BackwardsTimeException() {
        super("Sorry... The provided timeframe is impossible as the deadline is earlier than the start...");
    }

}
