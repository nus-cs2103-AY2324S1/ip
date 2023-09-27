package didier.exception;

/**
 * Thrown to indicate that a duplicate task is being attempted to be added
 * to the list.
 */
public class DuplicateTaskException extends DidierException {

    public DuplicateTaskException() {
        super("This task already exists in the list. ");
    }
}
