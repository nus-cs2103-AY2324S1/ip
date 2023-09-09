package didier.exception;

public class DuplicateTaskException extends DidierException {

    public DuplicateTaskException() {
        super("This task already exists in the list. ");
    }
}
