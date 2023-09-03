package VedaExceptions;

/**
 * NoDescriptionException is a form of IncorrectInputException that is thrown when the user omits certain description
 * in their input.
 *
 * @author Sebastian Tay
 */
public class NoDescriptionException extends IncorrectInputException {
    public NoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
