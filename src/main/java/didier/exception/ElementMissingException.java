package didier.exception;

public class ElementMissingException extends DidierException {

    public ElementMissingException(String element) {
        super("The " + element + " of the task is missing. ");
    }
}
