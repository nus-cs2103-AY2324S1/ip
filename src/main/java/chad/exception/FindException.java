package chad.exception;

/**
 * Represents an error that occurred during the initialisation of a find command.
 */
public class FindException extends CommandException {
    public FindException() {
        super("find QUERY_STRING");
    }
}
