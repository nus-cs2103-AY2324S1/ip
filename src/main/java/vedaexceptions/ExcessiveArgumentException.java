package vedaexceptions;

/**
 * ExcessiveArgumentException is a form of IncorrectInputException that is thrown when the user provides more arguments
 * than the command requires.
 *
 * @author Sebastian Tay
 */
public class ExcessiveArgumentException extends IncorrectInputException {
    public ExcessiveArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
