package exception;

/**
 * Checked Exception for the event where there are missing index arguments.
 */
public class MissingIndexException extends MissingArgumentException {
    @Override
    public String toString() {
        return "The following arguments are missing: index of the task";
    }
}
