package exceptions;

/**
 * IncorrectEventInputException is used when the user inputs invalid argument while trying to add an event.
 *
 * @author Sebastian Tay
 */
public class IncorrectEventInputException extends IncorrectInputException {
    public IncorrectEventInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Correct usage: event {description} /from {dd/MM/yyyy HHmm} /to {dd/MM/yyyy HHmm}";
    }
}
