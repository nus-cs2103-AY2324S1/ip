package exception;

/**
 * Checked Exception for the event where there are missing keyword arguments.
 */
public class MissingKeywordException extends MissingArgumentException {
    @Override
    public String toString() {
        return "The following arguments are missing: keyword of the task";
    }
}
