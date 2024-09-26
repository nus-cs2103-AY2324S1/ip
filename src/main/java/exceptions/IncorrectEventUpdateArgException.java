package exceptions;

/**
 * IncorrectEventUpdateArgException is used when an event task to be edited has invalid arguments.
 *
 * @author Sebastian Tay
 */
public class IncorrectEventUpdateArgException extends IncorrectInputException {
    public IncorrectEventUpdateArgException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Correct usage: update {index} {description} /from {dd/MM/yyyy HHmm} /to {dd/MM/yyyy HHmm}";
    }
}
