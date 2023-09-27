package didier.exception;

/**
 * Thrown to indicate that the user has not provided all the elements necessary for the command they
 * requested to be executed.
 */
public class ElementMissingException extends DidierException {

    public ElementMissingException(String element) {
        super("The " + element + " of the task is missing. ");
    }
}
