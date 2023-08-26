package didier.exception;

public class ElementMissingException extends DidierException {

    public ElementMissingException(String element) {
        super("The " + element + " of the didier.task is missing. ");
    }
}
